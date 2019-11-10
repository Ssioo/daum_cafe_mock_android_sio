package com.softsquared.softsquared_daum_cafe.src.signin.interfaces;

import com.softsquared.softsquared_daum_cafe.src.signin.models.SignInResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SignInRetrofitInterface {

    @GET("/user")
    Call<SignInResponse> getSignIn(@Query("email") String email,
                                   @Query("password") String password);
}
