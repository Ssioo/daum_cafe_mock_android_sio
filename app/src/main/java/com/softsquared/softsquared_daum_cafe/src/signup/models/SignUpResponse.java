package com.softsquared.softsquared_daum_cafe.src.signup.models;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("isSuccess")
    private boolean success;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public SignUpResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
