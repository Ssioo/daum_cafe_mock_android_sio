package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.interfaces;

import com.google.android.material.tabs.TabLayout;

public interface MyCafeFragmentView extends TabLayout.OnTabSelectedListener {

    void validateSuccess(String text);

    void validateFailure(String message);
}
