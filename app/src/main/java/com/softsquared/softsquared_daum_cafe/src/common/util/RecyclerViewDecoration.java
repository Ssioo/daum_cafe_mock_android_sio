package com.softsquared.softsquared_daum_cafe.src.common.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {
    private final int divHeight;
    private final int divWidth;

    public RecyclerViewDecoration(int divHeight, int divWidth) {
        this.divHeight = divHeight;
        this.divWidth = divWidth;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = divHeight;
        }
        outRect.right = divWidth;
        outRect.left = divWidth;
    }

}
