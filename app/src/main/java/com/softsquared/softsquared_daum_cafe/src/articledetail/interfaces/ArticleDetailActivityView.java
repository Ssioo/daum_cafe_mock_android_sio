package com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces;

import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsquared.softsquared_daum_cafe.src.articledetail.models.ArticleDetailResponse;

import java.util.ArrayList;

public interface ArticleDetailActivityView extends SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    void validateSuccess(ArrayList<ArticleDetailResponse.Result> results);

    void validateFailure(String message);
}
