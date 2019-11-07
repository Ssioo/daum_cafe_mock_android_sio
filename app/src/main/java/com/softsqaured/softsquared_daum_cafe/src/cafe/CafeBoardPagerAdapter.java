package com.softsqaured.softsquared_daum_cafe.src.cafe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsqaured.softsquared_daum_cafe.src.cafe.fragments.allarticle.AllArticlesBoard;
import com.softsqaured.softsquared_daum_cafe.src.cafe.fragments.populararticle.PopularArticlesBoard;

public class CafeBoardPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;

    public CafeBoardPagerAdapter(@NonNull FragmentManager fm, int pagecount) {
        super(fm);
        this.mPageCount = pagecount;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllArticlesBoard.newInstance();
            case 1:
                return PopularArticlesBoard.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
