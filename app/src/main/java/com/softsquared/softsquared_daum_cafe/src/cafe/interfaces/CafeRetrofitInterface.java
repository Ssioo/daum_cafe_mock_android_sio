package com.softsquared.softsquared_daum_cafe.src.cafe.interfaces;

import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;
import com.softsquared.softsquared_daum_cafe.src.common.models.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CafeRetrofitInterface {

    @GET("/{cafename}/board")
    Call<CafeResponse> getArticles(@Path("cafename") String cafeName);
}
