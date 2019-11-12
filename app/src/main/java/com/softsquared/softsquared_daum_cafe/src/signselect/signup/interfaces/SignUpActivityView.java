package com.softsquared.softsquared_daum_cafe.src.signselect.signup.interfaces;

import android.view.View;

public interface SignUpActivityView extends View.OnClickListener {
    void validateSuccess(String text);

    void validateFailure(String message);
}
