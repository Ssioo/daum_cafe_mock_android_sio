package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.interfaces;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.PopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PopularRetrofitInterface {

    @GET("/")
    Call<PopularResponse> getPopular();
}
