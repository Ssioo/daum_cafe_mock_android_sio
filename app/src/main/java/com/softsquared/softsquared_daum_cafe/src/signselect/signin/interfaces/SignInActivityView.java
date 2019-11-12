package com.softsquared.softsquared_daum_cafe.src.signselect.signin.interfaces;

import android.view.View;

public interface SignInActivityView extends View.OnClickListener{

    void validateSuccess(String token, String id, String name);

    void validateFailure(String message);
}
