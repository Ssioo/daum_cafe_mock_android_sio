package com.softsqaured.softsquared_daum_cafe.src.signin.interfaces;

import android.view.View;

public interface SignInActivityView extends View.OnClickListener{

    void validateSuccess(String text);

    void validateFailure(String message);
}
