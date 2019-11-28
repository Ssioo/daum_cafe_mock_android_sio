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
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ArticleDetailRetrofitService {
    @GET("board/{boardId}")
    Call<ArticleDetailResponse> getArticleDetail(@Path("boardId") int boardId);

    @DELETE("board/{boardId}")
    Call<ArticleDetailResponse> deleteArticle(@Path("boardId") int boardId);

    @GET("category")
    Call<CategoryResponse> getCategories();

    @POST("board/{boardId}/comment")
    Call<CommentResponse> postComment(@Path("boardId") int boardId, @Body CommentRequest commentRequest);

    @PATCH("board/{boardId}/modifyComment/{commentId}")
    Call<CommentResponse> patchComment(@Path("boardId") int boardId, @Path("commentId") int commentId, @Body CommentRequest commentRequest);

    @DELETE("board/{boardId}/comment/{commentId}")
    Call<CommentResponse> deleteComment(@Path("boardId") int boardId, @Path("commentId") int commentId);
}
