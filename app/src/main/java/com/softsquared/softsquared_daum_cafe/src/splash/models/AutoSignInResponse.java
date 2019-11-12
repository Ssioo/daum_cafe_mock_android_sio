package com.softsquared.softsquared_daum_cafe.src.splash.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

public class AutoSignInResponse extends DefaultResponse {
    @SerializedName("name")
    private String userName;

    public String getUserName() {
        return userName;
    }

}
