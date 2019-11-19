package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.interfaces;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model.CafeListResponse;

import java.util.ArrayList;

public interface CafeListFragmentView extends SwipeRefreshLayout.OnRefreshListener {

    void validateSuccess(ArrayList<CafeListResponse.Result> results);

    void validateFailure(String message);
}
