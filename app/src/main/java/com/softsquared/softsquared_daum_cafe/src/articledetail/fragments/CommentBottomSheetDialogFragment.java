package com.softsquared.softsquared_daum_cafe.src.articledetail.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces.CommentListAdapterView;

public class CommentBottomSheetDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    final CommentListAdapterView mCommentListAdapterView;

    private LinearLayout llItemFirst;
    private LinearLayout llItemSecond;
    private LinearLayout llItemThird;

    private int mCommentId;

    public CommentBottomSheetDialogFragment(CommentListAdapterView mCommentListAdapterView, int commentId) {
        this.mCommentListAdapterView = mCommentListAdapterView;
        mCommentId = commentId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_bottomsheet, container, false);

        /* findViewByID */
        llItemFirst = view.findViewById(R.id.ll_bottom_sheet_first_comment);
        llItemSecond = view.findViewById(R.id.ll_bottom_sheet_second_comment);
        llItemThird = view.findViewById(R.id.ll_bottom_sheet_third_comment);

        /* Set On Click Listener */
        llItemFirst.setOnClickListener(this);
        llItemSecond.setOnClickListener(this);
        llItemThird.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bottom_sheet_first_comment:
                break;
            case R.id.ll_bottom_sheet_second_comment:
                // 댓글 수정
                mCommentListAdapterView.onPatchClick(mCommentId);
                dismiss();
                break;
            case R.id.ll_bottom_sheet_third_comment:
                // 댓글 삭제
                mCommentListAdapterView.onDeleteClick(mCommentId);
                dismiss();
                break;
        }
    }
}
