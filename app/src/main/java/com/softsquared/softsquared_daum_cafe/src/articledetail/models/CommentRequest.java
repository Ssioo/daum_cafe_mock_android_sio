package com.softsquared.softsquared_daum_cafe.src.articledetail.models;

import com.google.gson.annotations.SerializedName;

public class CommentRequest {
    @SerializedName("contents") private String comment;

    public CommentRequest(String comment) {
        this.comment = comment;
    }
}
