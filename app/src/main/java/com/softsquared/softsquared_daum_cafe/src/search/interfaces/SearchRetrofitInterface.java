package com.softsquared.softsquared_daum_cafe.src.search.interfaces;

import com.softsquared.softsquared_daum_cafe.src.search.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchRetrofitInterface {
    @GET("search/board")
    Call<SearchResponse> getBoards();
}
