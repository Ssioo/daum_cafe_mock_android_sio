package com.softsquared.softsquared_daum_cafe.src.search.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class SearchResponse extends DefaultResponse {

    @SerializedName("result") private ArrayList<Result> results;

    public static class Result {
        @SerializedName("title") private String title;
        @SerializedName("contents") private String contents;
        @SerializedName("name") private String userId;

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public String getUserId() {
            return userId;
        }
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
