package com.softsquared.softsquared_daum_cafe.src.articledetail.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class ArticleDetailResponse extends DefaultResponse {

    @SerializedName("result")
    private ArrayList<Result> results;

    public class Result {
        @SerializedName("title") private String title;
        @SerializedName("contents") private String cotents;
        @SerializedName("userId") private String userId;
        @SerializedName("img") private String imgUri;
        @SerializedName("createdAt") private String createdAt;

        public String getTitle() {
            return title;
        }

        public String getCotents() {
            return cotents;
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
