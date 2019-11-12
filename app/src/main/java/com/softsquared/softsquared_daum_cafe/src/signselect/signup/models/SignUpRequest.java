package com.softsquared.softsquared_daum_cafe.src.signselect.signup.models;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("id")
    private String id;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;

    public SignUpRequest(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

}
