package com.softsqaured.softsquared_daum_cafe.src.main.interfaces;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public interface MainActivityView extends BottomNavigationView.OnNavigationItemSelectedListener{

    void validateSuccess(String text);

    void validateFailure(String message);
}
