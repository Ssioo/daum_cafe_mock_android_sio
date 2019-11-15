package com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces;

import com.softsquared.softsquared_daum_cafe.src.articledetail.models.ArticleDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleDetailRetrofitService {
    @GET("board/{boardid}")
    Call<ArticleDetailResponse> getArticleDetail(@Path("boardid") int boardid);
}
