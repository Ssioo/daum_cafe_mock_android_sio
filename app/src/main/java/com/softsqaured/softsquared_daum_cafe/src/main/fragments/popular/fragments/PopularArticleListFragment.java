package com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.adapter.RecyclerViewDecoration;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.fragments.interfaces.PopularArticleListFragmentView;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.models.Article;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.adapter.PopularArticleListAdapter;

import java.util.ArrayList;

public class PopularArticleListFragment extends BaseFragment implements PopularArticleListFragmentView {

    private SwipeRefreshLayout srlPopular;
    private RecyclerView rvPopular;
    private ArrayList<Article> articlesPopular;

    public PopularArticleListFragment(ArrayList<Article> articlesPopular) {
        this.articlesPopular = articlesPopular;
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
        rvPopular.setAdapter(new PopularArticleListAdapter(articlesPopular, getActivity()));
        rvPopular.addItemDecoration(new RecyclerViewDecoration(30));

        /* SwipeRefreshLayout On Refresh Listener */
        srlPopular.setOnRefreshListener(this);


        return view;
    }

    @Override
    public void onRefresh() {
        srlPopular.setRefreshing(false);
    }
}
