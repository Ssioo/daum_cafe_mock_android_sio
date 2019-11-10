package com.softsquared.softsquared_daum_cafe.src.signin;

import com.softsquared.softsquared_daum_cafe.src.signin.interfaces.SignInActivityView;
import com.softsquared.softsquared_daum_cafe.src.signin.interfaces.SignInRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.signin.models.SignInResponse;

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
        mainRetrofitInterface.getSignIn(email, password).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                final SignInResponse signInResponse = response.body();
                if (signInResponse == null || !signInResponse.isSuccess()) {
                    signInActivityView.validateFailure(null);
                    return;
                }

                signInActivityView.validateSuccess(signInResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                signInActivityView.validateFailure(null);
            }
        });
    }
}
