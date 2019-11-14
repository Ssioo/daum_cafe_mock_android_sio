package com.softsquared.softsquared_daum_cafe.src.cafe.adpater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsquared.softsquared_daum_cafe.src.cafe.fragments.article.ArticlesBoardFragment;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.ArticleOnList;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;

import java.util.ArrayList;

public class CafeBoardPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;
    private ArrayList<ArrayList<CafeResponse.Result>> mArticleListList;

    public CafeBoardPagerAdapter(@NonNull FragmentManager fm, int pagecount, ArrayList<ArrayList<CafeResponse.Result>> articleListList) {
        super(fm);
        this.mPageCount = pagecount;
        this.mArticleListList = articleListList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ArticlesBoardFragment.newInstance(mArticleListList.get(position));
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
