package com.softsquared.softsquared_daum_cafe.src.signselect.signin.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

public class SignInResponse extends DefaultResponse {
    @SerializedName("jwt")
    private String token;

    @SerializedName("userInfo")
    private UserInfo userInfo;

    public SignInResponse() {
    }

    public String getToken() {
        return token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public static class UserInfo {
        @SerializedName("iduser")
        private int iduser;
        @SerializedName("id")
        private String userId;
        @SerializedName("pwd")
        private String hashPw;
        @SerializedName("name")
        private String name;
        @SerializedName("status")
        private String status;

        public int getIduser() {
            return iduser;
        }

        public String getUserId() {
            return userId;
        }

        public String getHashPw() {
            return hashPw;
        }

        public String getName() {
            return name;
        }

        public String getStatus() {
            return status;
        }
    }
}
