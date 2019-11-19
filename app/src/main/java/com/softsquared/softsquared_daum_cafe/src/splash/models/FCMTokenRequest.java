package com.softsquared.softsquared_daum_cafe.src.splash.models;

import com.google.gson.annotations.SerializedName;

public class FCMTokenRequest {
    @SerializedName("fcm_token") String token;

    public FCMTokenRequest(String token) {
        this.token = token;
    }
}
