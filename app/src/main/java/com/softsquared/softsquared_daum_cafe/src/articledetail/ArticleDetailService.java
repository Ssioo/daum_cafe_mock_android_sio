package com.softsquared.softsquared_daum_cafe.src.articledetail;

import com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces.ArticleDetailActivityView;
import com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces.ArticleDetailRetrofitService;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.ArticleDetailResponse;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.CommentRequest;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.CommentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class ArticleDetailService {
    final ArticleDetailActivityView mArticleDetailActivityView;

    public ArticleDetailService(ArticleDetailActivityView mArticleDetailActivityView) {
        this.mArticleDetailActivityView = mArticleDetailActivityView;
    }

    public void getArticleDetail(int boardId) {
        final ArticleDetailRetrofitService articleDetailRetrofitService = getRetrofit().create(ArticleDetailRetrofitService.class);
        articleDetailRetrofitService.getArticleDetail(boardId).enqueue(new Callback<ArticleDetailResponse>() {
            @Override
            public void onResponse(Call<ArticleDetailResponse> call, Response<ArticleDetailResponse> response) {
                final ArticleDetailResponse articleDetailResponse = response.body();
                if (articleDetailResponse == null || !articleDetailResponse.getIsSuccess() || articleDetailResponse.getResults() == null) {
                    mArticleDetailActivityView.validateFailure(null);
                    return;
                }
                mArticleDetailActivityView.validateSuccess(articleDetailResponse.getResults());
            }

            @Override
            public void onFailure(Call<ArticleDetailResponse> call, Throwable t) {
                t.printStackTrace();
                mArticleDetailActivityView.validateFailure(null);
            }
        });
    }

    public void postComment(int boardId, String comment) {
        final ArticleDetailRetrofitService articleDetailRetrofitService = getRetrofit().create(ArticleDetailRetrofitService.class);
        articleDetailRetrofitService.postComment(boardId, new CommentRequest(comment)).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                final CommentResponse commentResponse = response.body();
                if (commentResponse == null || !commentResponse.getIsSuccess()) {
                    mArticleDetailActivityView.validateFailure((commentResponse == null) ? null : commentResponse.getMessage());
                    return;
                }

                mArticleDetailActivityView.validateWriteCommentSuccess(null);
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                t.printStackTrace();
                mArticleDetailActivityView.validateFailure(null);
            }
        });
    }
}
