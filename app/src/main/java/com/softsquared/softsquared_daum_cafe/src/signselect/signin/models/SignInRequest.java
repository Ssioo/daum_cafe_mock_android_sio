package com.softsquared.softsquared_daum_cafe.src.signselect.signin.models;

import com.google.gson.annotations.SerializedName;

public class SignInRequest {
    @SerializedName("id")
    private String id;
    @SerializedName("password")
    private String password;
    @SerializedName("device") private String deviceId;

    public SignInRequest(String id, String password, String deviceId) {
        this.id = id;
        this.password = password;
        this.deviceId = deviceId;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
