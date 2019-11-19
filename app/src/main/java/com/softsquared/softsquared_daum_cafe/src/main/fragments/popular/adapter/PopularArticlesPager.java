package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.fragments.PopularArticleListFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.PopularResponse;

import java.util.ArrayList;

public class PopularArticlesPager extends FragmentStatePagerAdapter {

    ArrayList<ArrayList<PopularResponse.Result>> articles;
    private int viewType;


    public PopularArticlesPager(@NonNull FragmentManager fm, ArrayList<ArrayList<PopularResponse.Result>> articles, int viewType) {
        super(fm);
        this.articles = articles;
        this.viewType = viewType;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position % 3) {
            case 0:
                return PopularArticleListFragment.newInstanceNow(articles.get(0), viewType);
            case 1:
                return PopularArticleListFragment.newInstanceWeek(articles.get(1), viewType);
            case 2:
                return PopularArticleListFragment.newInstanceMonth(articles.get(2), viewType);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 300;
    }
}
