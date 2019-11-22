package com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces;

import com.softsquared.softsquared_daum_cafe.src.articledetail.models.ArticleDetailResponse;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.CommentRequest;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.CommentResponse;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.Category;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ArticleDetailRetrofitService {
    @GET("board/{boardid}")
    Call<ArticleDetailResponse> getArticleDetail(@Path("boardid") int boardid);

    @POST("board/{boardid}/comment")
    Call<CommentResponse> postComment(@Path("boardid") int boardid, @Body CommentRequest commentRequest);

    @DELETE("board/{boardid}")
    Call<ArticleDetailResponse> deleteArticle(@Path("boardid") int boardid);

    @GET("category")
    Call<CategoryResponse> getCategories();
}
