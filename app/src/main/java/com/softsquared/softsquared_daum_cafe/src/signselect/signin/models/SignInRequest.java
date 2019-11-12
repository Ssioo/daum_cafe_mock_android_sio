package com.softsquared.softsquared_daum_cafe.src.signselect.signin.models;

import com.google.gson.annotations.SerializedName;

public class SignInRequest {
    @SerializedName("id")
    private String id;
    @SerializedName("password")
    private String password;

    public SignInRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
