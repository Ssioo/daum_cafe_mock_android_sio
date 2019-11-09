package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.interfaces;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public interface CafeListFragmentView extends SwipeRefreshLayout.OnRefreshListener {

    void validateSuccess(String text);

    void validateFailure(String message);
}
