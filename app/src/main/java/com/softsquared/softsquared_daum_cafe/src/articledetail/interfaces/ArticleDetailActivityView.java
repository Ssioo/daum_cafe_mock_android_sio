package com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces;

import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsquared.softsquared_daum_cafe.src.articledetail.models.ArticleDetailResponse;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CategoryResponse;

import java.util.ArrayList;

public interface ArticleDetailActivityView extends SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, View.OnLayoutChangeListener {
    void validateSuccess(ArrayList<ArticleDetailResponse.Result> results);

    void validateFailure(String message);

    void validateWriteCommentSuccess(String message);

    void validateGetCategoriesSuccess(ArrayList<CategoryResponse.Result> results);

    void validateDeleteSuccess(String message);

    void startActivityFromDialogFragment();

    void startDeleteProcessFromFragment();
}
