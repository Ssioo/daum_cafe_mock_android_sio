package com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces;

import android.view.View;

public interface CommentListAdapterView {

    void onPatchClick(int cid, String cContents);
    void onDeleteClick(int cid);
}
