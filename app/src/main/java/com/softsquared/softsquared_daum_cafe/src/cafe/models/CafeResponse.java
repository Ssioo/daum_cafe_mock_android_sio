package com.softsquared.softsquared_daum_cafe.src.cafe.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class CafeResponse extends DefaultResponse {
    @SerializedName("result")
    ArrayList<Result> results;

    public static class Result {
        @SerializedName("idboard") int boardId;
        @SerializedName("title") String title;
        @SerializedName("categotyType") String categoryType;
        @SerializedName("cafeName") String cafeName;
        @SerializedName("createAt") String createdAtTimeZone;
        @SerializedName("id") String userId;
        @SerializedName("img") String imgUri;
        @SerializedName("COUNT(b.views)") int viewCount;
        @SerializedName("commentCount") int commentCount;
        @SerializedName("createdAt") String createdAt;

        public String getTitle() {
            return title;
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

        public int getBoardId() {
            return boardId;
        }

        public String getCategoryType() {
            return categoryType;
        }

        public String getCafeName() {
            return cafeName;
        }

        public String getCreatedAtTimeZone() {
            return createdAtTimeZone;
        }

        public int getViewCount() {
            return viewCount;
        }

        public int getCommentCount() {
            return commentCount;
        }
    }

    public ArrayList<Result> getResults() {
        return results;
    }


}
