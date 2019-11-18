package com.softsquared.softsquared_daum_cafe.src.signselect.signin;

import com.softsquared.softsquared_daum_cafe.src.signselect.signin.interfaces.SignInActivityView;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.interfaces.SignInRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.models.SignInRequest;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class SignInService {
    private final SignInActivityView signInActivityView;

    public SignInService(SignInActivityView signInActivityView) {
        this.signInActivityView = signInActivityView;
    }

    void getSignIn(String email, String password) {
        final SignInRetrofitInterface mainRetrofitInterface = getRetrofit().create(SignInRetrofitInterface.class);
        mainRetrofitInterface.getSignIn(new SignInRequest(email, password)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                final SignInResponse signInResponse = response.body();

                // 로그인 실패 시
                if (signInResponse == null || !signInResponse.getIsSuccess()) {
                    signInActivityView.validateFailure(null);
                    return;
                }

                // userinfo가 null이면, token으로 로그인했을 수도 있음.
                if (signInResponse.getUserInfo() == null && signInResponse.getName() == null) {
                    signInActivityView.validateFailure(null);
                    return;
                }
                if (signInResponse.getUserInfo() != null) {
                    signInActivityView.validateSuccessWithNewToken(signInResponse.getToken(), signInResponse.getUserInfo().getUserId(), signInResponse.getUserInfo().getName());
                }
                if (signInResponse.getName() != null) {
                    signInActivityView.validateSuccessWithoutNewToken(signInResponse.getName());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                signInActivityView.validateFailure(null);
            }
        });
    }

    void getUserInfo() {

    }
}
