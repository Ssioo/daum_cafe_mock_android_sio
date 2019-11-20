package com.softsquared.softsquared_daum_cafe.src.cafe.interfaces;

import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CafeRetrofitInterface {

    @GET("/{cafename}/board")
    Call<CafeResponse> getArticles(@Path("cafename") String cafeName);

    @GET("category")
    Call<CategoryResponse> getCategories();
}
