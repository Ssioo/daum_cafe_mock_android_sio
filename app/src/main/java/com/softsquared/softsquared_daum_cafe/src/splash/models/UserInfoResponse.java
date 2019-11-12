package com.softsquared.softsquared_daum_cafe.src.splash.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

public class UserInfoResponse extends DefaultResponse {
    @SerializedName("userInfo")
    private UserInfo userInfo = new UserInfo();

    public static class UserInfo {
        @SerializedName("id")
        private String userEmail;
        @SerializedName("name")
        private String userName;

        public String getUserEmail() {
            return userEmail;
        }

        public String getUserName() {
            return userName;
        }
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
