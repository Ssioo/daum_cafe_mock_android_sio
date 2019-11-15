package com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces;

import android.net.Uri;
import android.view.View;

public interface WriteActivityView extends View.OnClickListener{
    void validateSuccess(String message);

    void validateFailure(String message);

    void validateImageSuccess(String url);

    void validateImageFailure(String message);
}
