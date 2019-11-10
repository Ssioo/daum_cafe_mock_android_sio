package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;
import com.softsquared.softsquared_daum_cafe.src.util.RecyclerViewDecoration;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.fragments.interfaces.PopularArticleListFragmentView;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.Article;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.adapter.PopularArticleListAdapter;

import java.util.ArrayList;

public class PopularArticleListFragment extends BaseFragment implements PopularArticleListFragmentView {

    private SwipeRefreshLayout srlPopular;
    private RecyclerView rvPopular;
    private ArrayList<Article> articlesPopular;
    private PopularArticleListAdapter palAdapter;

    private int viewType;

    public PopularArticleListFragment(ArrayList<Article> articlesPopular, int viewType) {
        this.articlesPopular = articlesPopular;
        this.viewType = viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public static PopularArticleListFragment newInstanceNow(ArrayList<Article> articlesPopular, int viewType) {
        return new PopularArticleListFragment(articlesPopular, viewType);
    }
    public static PopularArticleListFragment newInstanceWeek(ArrayList<Article> articlesPopular, int viewType) {
        return new PopularArticleListFragment(articlesPopular, viewType);
    }
    public static PopularArticleListFragment newInstanceMonth(ArrayList<Article> articlesPopular, int viewType) {
        return new PopularArticleListFragment(articlesPopular, viewType);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_list, container, false);

        /* findViewByID */
        srlPopular = view.findViewById(R.id.srl_popular);
        rvPopular = view.findViewById(R.id.rv_popular);

        /* RecyclerView */
        // dummy
        palAdapter = new PopularArticleListAdapter(articlesPopular, getActivity(), viewType);
        palAdapter.setAnimationsLocked(false);
        palAdapter.setDelayEnterAnimation(true);
        rvPopular.setAdapter(palAdapter);
        if (viewType == 0) {
            rvPopular.addItemDecoration(new RecyclerViewDecoration(30, 30));
        } else if (viewType == 1) {
            rvPopular.addItemDecoration(new RecyclerViewDecoration(1, 30));
        }

        /* SwipeRefreshLayout On Refresh Listener */
        srlPopular.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onRefresh() {
        palAdapter.setAnimationsLocked(false);
        palAdapter.setDelayEnterAnimation(false);
        palAdapter.notifyDataSetChanged();
        srlPopular.setRefreshing(false);
    }
}
