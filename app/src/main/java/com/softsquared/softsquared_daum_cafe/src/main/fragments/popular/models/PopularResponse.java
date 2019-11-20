package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class PopularResponse extends DefaultResponse {

    @SerializedName("result")
    private ArrayList<Result> results;

    public static class Result {
        @SerializedName("idboard") private int boardId;
        @SerializedName("title") private String title;
        @SerializedName("contents") private String contents;
        @SerializedName("img") private String img;
        @SerializedName("id") private String userId;

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public String getUserId() {
            return userId;
        }

        public int getBoardId() {
            return boardId;
        }

        public String getImg() {
            return img;
        }

        public Result(int boardId, String title, String contents, String img, String userId) {
            this.boardId = boardId;
            this.title = title;
            this.contents = contents;
            this.img = img;
            this.userId = userId;
        }
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public PopularResponse(ArrayList<Result> results) {
        this.results = results;
    }
}
