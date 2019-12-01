package com.softsquared.softsquared_daum_cafe.src.cafe.adpater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsquared.softsquared_daum_cafe.src.cafe.fragments.article.ArticlesBoardFragment;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;

import java.util.ArrayList;

public class CafeBoardPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;
    private ArrayList<ArrayList<CafeResponse.Result>> mArticleListList;
    private String mCafeName;

    public CafeBoardPagerAdapter(@NonNull FragmentManager fm, int pagecount, ArrayList<ArrayList<CafeResponse.Result>> articleListList, String cafeName) {
        super(fm);
        this.mPageCount = pagecount;
        this.mArticleListList = articleListList;
        mCafeName = cafeName;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ArticlesBoardFragment.newInstance(mArticleListList.get(position), mCafeName);
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
