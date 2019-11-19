package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.interfaces;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model.CafeListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CafeListRetrofitInterface {
    @GET("cafe")
    Call<CafeListResponse> getCafeList();
}
