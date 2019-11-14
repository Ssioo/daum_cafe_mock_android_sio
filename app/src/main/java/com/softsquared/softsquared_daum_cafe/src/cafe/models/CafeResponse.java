package com.softsquared.softsquared_daum_cafe.src.cafe.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class CafeResponse extends DefaultResponse {
    @SerializedName("result")
    ArrayList<Result> results;

    public class Result {
        @SerializedName("title")
        String title;
        @SerializedName("contents")
        String contents;
        @SerializedName("id")
        String userId;
        @SerializedName("img")
        String imgUri;
        @SerializedName("createdAt")
        String createdAt;

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public String getUserId() {
            return userId;
        }

        public String getImgUri() {
            return imgUri;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }

    public ArrayList<Result> getResults() {
        return results;
    }


}
