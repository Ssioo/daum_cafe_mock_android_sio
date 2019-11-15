package com.softsquared.softsquared_daum_cafe.src.signselect.signin;

import android.util.Log;

import com.softsquared.softsquared_daum_cafe.src.signselect.signin.interfaces.SignInActivityView;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.interfaces.SignInRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.models.SignInRequest;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

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
                if (signInResponse == null || !signInResponse.getIsSuccess()) {
                    signInActivityView.validateFailure(null);
                    return;
                }
                if (signInResponse.getUserInfo() == null) {
                    signInActivityView.validateFailure(null);
                    return;
                }
                signInActivityView.validateSuccess(signInResponse.getToken(), signInResponse.getUserInfo().getUserId(), signInResponse.getUserInfo().getName());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                signInActivityView.validateFailure(null);
            }
        });
    }
}
