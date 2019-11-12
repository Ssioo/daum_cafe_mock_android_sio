package com.softsquared.softsquared_daum_cafe.src.cafe.write.models;

import com.google.gson.annotations.SerializedName;

public class WriteRequest {
    @SerializedName("title") private String title;
    @SerializedName("contents") private String contents;
    @SerializedName("categoryname") private String categoryName;
    @SerializedName("cafeName") private String cafeName;
    @SerializedName("img") private String imgUrl;

    public WriteRequest(String title, String contents, String categoryName, String cafeName, String imgUrl) {
        this.title = title;
        this.contents = contents;
        this.categoryName = categoryName;
        this.cafeName = cafeName;
        this.imgUrl = imgUrl;
    }
}
