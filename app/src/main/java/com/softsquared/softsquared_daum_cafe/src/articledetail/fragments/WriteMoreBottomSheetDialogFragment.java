package com.softsquared.softsquared_daum_cafe.src.articledetail.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces.ArticleDetailActivityView;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.WriteActivity;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_EMAIL;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_NAME;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class WriteMoreBottomSheetDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private LinearLayout llBottomSheetFirst;
    private LinearLayout llBottomSheetSecond;
    private LinearLayout llBottomSheetThird;
    private LinearLayout llBottomSheetForth;
    private LinearLayout llBottomSheetFifth;
    private LinearLayout llBottomSheetSixth;

    private String mAuthor;
    final ArticleDetailActivityView mArticleDetailActivityView;

    public WriteMoreBottomSheetDialogFragment(String mAuthor, ArticleDetailActivityView mArticleDetailActivityView) {
        this.mAuthor = mAuthor;
        this.mArticleDetailActivityView = mArticleDetailActivityView;
    }

    public static WriteMoreBottomSheetDialogFragment newInstance(String author, ArticleDetailActivityView mArticleDetailActivityView) {
        WriteMoreBottomSheetDialogFragment fragment = new WriteMoreBottomSheetDialogFragment(author, mArticleDetailActivityView);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articledetail_bottomsheet, container, false);

        /* findViewByID */
        llBottomSheetFirst = view.findViewById(R.id.ll_bottom_sheet_first_articledetail);
        llBottomSheetSecond = view.findViewById(R.id.ll_bottom_sheet_second_articledetail);
        llBottomSheetThird = view.findViewById(R.id.ll_bottom_sheet_third_articledetail);
        llBottomSheetForth = view.findViewById(R.id.ll_bottom_sheet_forth_articledetail);
        llBottomSheetFifth = view.findViewById(R.id.ll_bottom_sheet_fifth_articledetail);
        llBottomSheetSixth = view.findViewById(R.id.ll_bottom_sheet_sixth_articledetail);

        /* Set On Click Listener */
        llBottomSheetFirst.setOnClickListener(this);
        llBottomSheetSecond.setOnClickListener(this);
        llBottomSheetThird.setOnClickListener(this);
        llBottomSheetForth.setOnClickListener(this);
        llBottomSheetFifth.setOnClickListener(this);
        llBottomSheetSixth.setOnClickListener(this);

        /* Init View */
        if (sSharedPreferences.getBoolean(USER_LOGINNED, false)) {
            llBottomSheetThird.setVisibility(View.VISIBLE);
            llBottomSheetForth.setVisibility(View.VISIBLE);
            if (sSharedPreferences.getString(USER_NAME, "").equals(mAuthor) || sSharedPreferences.getString(USER_EMAIL, "").equals(mAuthor)) {
                llBottomSheetFifth.setVisibility(View.VISIBLE);
                llBottomSheetSixth.setVisibility(View.VISIBLE);
            } else {
                llBottomSheetFifth.setVisibility(View.GONE);
                llBottomSheetSixth.setVisibility(View.GONE);
            }
        } else {
            llBottomSheetThird.setVisibility(View.GONE);
            llBottomSheetForth.setVisibility(View.GONE);
            llBottomSheetFifth.setVisibility(View.GONE);
            llBottomSheetSixth.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bottom_sheet_first_articledetail:
                break;
            case R.id.ll_bottom_sheet_second_articledetail:
                break;
            case R.id.ll_bottom_sheet_third_articledetail:
                break;
            case R.id.ll_bottom_sheet_forth_articledetail:
                break;
            case R.id.ll_bottom_sheet_fifth_articledetail:
                // 글 수정하기
                mArticleDetailActivityView.startActivityFromDialogFragment();
                dismiss();
                break;
            case R.id.ll_bottom_sheet_sixth_articledetail:
                // 글 삭제하기
                mArticleDetailActivityView.startDeleteProcessFromFragment();
                dismiss();
                break;
        }
    }
}
