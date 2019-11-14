package com.softsquared.softsquared_daum_cafe.src.cafe.interfaces;

import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;

import java.util.ArrayList;

public interface CafeActivityView
        extends View.OnClickListener,
        AppBarLayout.OnOffsetChangedListener,
        TabLayout.OnTabSelectedListener,
        SwipeRefreshLayout.OnRefreshListener{

    void validateSuccess(ArrayList<CafeResponse.Result> results);

    void validateFailure(String message);
}
