package com.softsquared.softsquared_daum_cafe.src.splash.interfaces;

import com.softsquared.softsquared_daum_cafe.src.splash.models.AutoSignInResponse;
import com.softsquared.softsquared_daum_cafe.src.splash.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SplashRetrofitInterface {
    @POST("app/signIn")
    Call<AutoSignInResponse> postAutoSignIn();

    @GET("user/info")
    Call<UserInfoResponse> getUserInfo();
}
