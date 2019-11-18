package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class PopularResponse extends DefaultResponse {

    @SerializedName("result")
    private ArrayList<Result> results;

    public static class Result {
        @SerializedName("title") private String title;
        @SerializedName("contents") private String contents;
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

        public Result(String title, String contents, String userId) {
            this.title = title;
            this.contents = contents;
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
