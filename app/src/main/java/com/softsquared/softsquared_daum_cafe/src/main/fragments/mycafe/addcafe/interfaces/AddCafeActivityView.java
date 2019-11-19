package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.interfaces;

import android.view.View;

public interface AddCafeActivityView extends View.OnClickListener {
    void validateSuccess(String text);

    void validateFailure(String message);
}
