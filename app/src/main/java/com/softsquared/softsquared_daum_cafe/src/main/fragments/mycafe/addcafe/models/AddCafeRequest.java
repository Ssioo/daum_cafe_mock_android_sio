package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.models;

import com.google.gson.annotations.SerializedName;

public class AddCafeRequest {
    @SerializedName("cafeName")
    String title;

    public AddCafeRequest(String title) {
        this.title = title;
    }
}
