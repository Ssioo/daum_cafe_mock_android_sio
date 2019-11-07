package com.softsqaured.softsquared_daum_cafe.src.cafe.interfaces;

import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public interface CafeActivityView
        extends View.OnClickListener,
        AppBarLayout.OnOffsetChangedListener,
        TabLayout.OnTabSelectedListener,
        SwipeRefreshLayout.OnRefreshListener{

    void validateSuccess(String text);

    void validateFailure(String message);
}
