package com.softsquared.softsquared_daum_cafe.src.article_detail;

import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.article_detail.interfaces.ArticleDetailActivityView;

public class ArticleDetailActivity extends BaseActivity implements ArticleDetailActivityView {

    private SwipeRefreshLayout srlActicleDeatil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        /* findViewByID */
        srlActicleDeatil = findViewById(R.id.srl_articledetail);

        /* RefreshLayout */
        srlActicleDeatil.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        srlActicleDeatil.setRefreshing(false);
    }
}
