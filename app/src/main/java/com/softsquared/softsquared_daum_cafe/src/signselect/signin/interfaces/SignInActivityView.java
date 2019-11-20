package com.softsquared.softsquared_daum_cafe.src.signselect.signin.interfaces;

import android.view.View;

public interface SignInActivityView extends View.OnClickListener{

    void validateSuccessWithNewToken(String token, String id, String name);

    void validateSuccessWithoutNewToken(String name, String id);

    void validateFailure(String message);
}
