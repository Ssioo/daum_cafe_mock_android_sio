package com.softsquared.softsquared_daum_cafe.src.cafe.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class CategoryResponse extends DefaultResponse {

    @SerializedName("result")
    private ArrayList<Result> results;

    public ArrayList<Result> getResults() {
        return results;
    }

    public static class Result {
        @SerializedName("categoryname") private String categoryName;

        public String getCategoryName() {
            return categoryName;
        }
    }
}
