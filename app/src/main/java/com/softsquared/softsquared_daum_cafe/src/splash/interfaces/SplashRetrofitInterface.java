package com.softsquared.softsquared_daum_cafe.src.splash.interfaces;

import com.softsquared.softsquared_daum_cafe.src.splash.models.AutoSignInResponse;
import com.softsquared.softsquared_daum_cafe.src.splash.models.FCMTokenRequest;
import com.softsquared.softsquared_daum_cafe.src.splash.models.FCMTokenResponse;
import com.softsquared.softsquared_daum_cafe.src.splash.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SplashRetrofitInterface {
    @POST("app/signIn")
    Call<AutoSignInResponse> postAutoSignIn();

    @GET("user/info")
    Call<UserInfoResponse> getUserInfo();

     // FCM Token 보내는 API
    @POST("fcmtoken")
    Call<FCMTokenResponse> postToken(@Body FCMTokenRequest tokenRequest);
}
