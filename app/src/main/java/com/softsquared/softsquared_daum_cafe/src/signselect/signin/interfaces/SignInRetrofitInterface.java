package com.softsquared.softsquared_daum_cafe.src.signselect.signin.interfaces;

import com.softsquared.softsquared_daum_cafe.src.signselect.signin.models.SignInRequest;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.models.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignInRetrofitInterface {

    @POST("app/signIn")
    Call<SignInResponse> getSignIn(@Body SignInRequest singInRequest);
}
