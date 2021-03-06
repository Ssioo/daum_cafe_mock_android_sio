package com.softsquared.softsquared_daum_cafe.src.articledetail.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class ArticleDetailResponse extends DefaultResponse {

    @SerializedName("result")
    private ArrayList<Result> results;

    public static class Result {
        @SerializedName("title") private String title;
        @SerializedName("contents") private String contents;
        @SerializedName("boardUser") private String userId;
        @SerializedName("img") private String imgUri;
        @SerializedName("createdAt") private String createdAt;
        @SerializedName("views") private int viewCount;
        @SerializedName("commentUser") private String commentUser;
        @SerializedName("content") private String commentContents;
        @SerializedName("createAt") private String commentCreatedAt;
        @SerializedName("commentid") private int commentId;

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

        public int getViewCount() {
            return viewCount;
        }

        public String getCommentCreatedAt() {
            return commentCreatedAt;
        }

        public String getCommentContents() {
            return commentContents;
        }

        public String getCommentUser() {
            return commentUser;
        }

        public int getCommentId() {
            return commentId;
        }
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
