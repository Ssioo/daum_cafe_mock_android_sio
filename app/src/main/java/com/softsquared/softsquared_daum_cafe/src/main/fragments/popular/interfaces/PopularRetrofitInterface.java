package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.interfaces;

import retrofit2.http.GET;

public interface PopularRetrofitInterface {

    @GET
    void getPopular();
}
