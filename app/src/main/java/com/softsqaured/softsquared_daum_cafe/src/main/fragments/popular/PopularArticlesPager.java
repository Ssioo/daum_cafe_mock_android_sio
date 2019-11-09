package com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.fragments.PopularArticleListFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.models.Article;

import java.util.ArrayList;

public class PopularArticlesPager extends FragmentStatePagerAdapter {

    ArrayList<ArrayList<Article>> articles;


    public PopularArticlesPager(@NonNull FragmentManager fm, ArrayList<ArrayList<Article>> articles) {
        super(fm);
        this.articles = articles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        PopularArticleListFragment popularArticleListFragment = new PopularArticleListFragment(articles.get(position));
        return popularArticleListFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
