package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.interfaces;

import android.view.View;

import com.google.android.material.tabs.TabLayout;

public interface MyCafeActivityView extends TabLayout.OnTabSelectedListener, View.OnClickListener {

    void validateSuccess(String text);

    void validateFailure(String message);
}
