package com.softsquared.softsquared_daum_cafe.src.signin.models;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {
    @SerializedName("jwt")
    private String token;

    @SerializedName("userInfo")
    private String userInfo;

    @SerializedName("isSuccess")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    public SignInResponse() {
    }

    public String getToken() {
        return token;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
