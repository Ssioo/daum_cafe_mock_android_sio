package com.softsquared.softsquared_daum_cafe.src.signselect.signup;

import com.softsquared.softsquared_daum_cafe.src.signselect.signup.interfaces.SignUpActivityView;
import com.softsquared.softsquared_daum_cafe.src.signselect.signup.interfaces.SignUpRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.signselect.signup.models.SignUpRequest;
import com.softsquared.softsquared_daum_cafe.src.signselect.signup.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class SignUpService {
    final SignUpActivityView mSignUpActivityView;

    public SignUpService(SignUpActivityView mSignUpActivityView) {
        this.mSignUpActivityView = mSignUpActivityView;
    }

    void getSignIn(String email, String password, String name) {
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.getResponse(new SignUpRequest(email, password, name)).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null || !signUpResponse.getIsSuccess()) {
                    mSignUpActivityView.validateFailure((signUpResponse == null) ? null : signUpResponse.getMessage());
                    return;
                }

                mSignUpActivityView.validateSuccess(signUpResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure(null);
            }
        });
    }
}
