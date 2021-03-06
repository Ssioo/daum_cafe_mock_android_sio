package com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces;

import android.view.View;
import android.widget.AdapterView;

public interface WriteActivityView extends View.OnClickListener {
    void validateUploadSuccess(String message);

    void validateUploadFailure(String message);

    void validateUploadImageSuccess(String url);

    void validateUploadImageFailure(String message);

    void validateDownloadSuccess(String message);

    void validateDownloadFailure(String message);
}
