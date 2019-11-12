package com.softsquared.softsquared_daum_cafe.src.splash.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class UserInfoResponse extends DefaultResponse {
    @SerializedName("userInfo")
    private ArrayList<UserInfo> userInfo;

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

    public ArrayList<UserInfo> getUserInfo() {
        return userInfo;
    }
}
