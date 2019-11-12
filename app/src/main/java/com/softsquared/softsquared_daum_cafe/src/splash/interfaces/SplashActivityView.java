package com.softsquared.softsquared_daum_cafe.src.splash.interfaces;

public interface SplashActivityView {

    void validateSuccess(String email, String name);

    void validateFailure(String message);
}
