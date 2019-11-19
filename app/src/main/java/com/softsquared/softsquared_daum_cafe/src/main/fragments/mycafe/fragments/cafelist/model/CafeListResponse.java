package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model;

import com.google.gson.annotations.SerializedName;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import java.util.ArrayList;

public class CafeListResponse extends DefaultResponse {

    @SerializedName("result")
    private ArrayList<Result> results;

    public static class Result {
        @SerializedName("name") private String cafeName;

        public String getCafeName() {
            return cafeName;
        }
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
