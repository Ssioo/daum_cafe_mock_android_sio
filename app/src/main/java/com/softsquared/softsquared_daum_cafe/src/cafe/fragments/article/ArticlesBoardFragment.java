package com.softsquared.softsquared_daum_cafe.src.cafe.fragments.article;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;
import com.softsquared.softsquared_daum_cafe.src.cafe.fragments.article.interfaces.ArticlesBoardFragmentView;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.ArticleOnList;
import com.softsquared.softsquared_daum_cafe.src.cafe.fragments.article.adapter.ArticleOnListListAdapter;

import java.util.ArrayList;

public class ArticlesBoardFragment extends BaseFragment implements ArticlesBoardFragmentView {

    private RecyclerView rvArticlesCafe;
    private SwipeRefreshLayout srlArticlesCafe;

    private ArrayList<ArticleOnList> mArticleOnLists;

    public ArticlesBoardFragment(ArrayList<ArticleOnList> mArticleOnLists) {
        this.mArticleOnLists = mArticleOnLists;
    }

    public static ArticlesBoardFragment newInstance(ArrayList<ArticleOnList> articleOnLists) {
        ArticlesBoardFragment fragment = new ArticlesBoardFragment(articleOnLists);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe, container, false);

        /* findViewByID */
        rvArticlesCafe = view.findViewById(R.id.rv_articlelist_cafe);
        srlArticlesCafe = view.findViewById(R.id.srl_cafe);

        /* RecyclerView */
        rvArticlesCafe.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvArticlesCafe.setAdapter(new ArticleOnListListAdapter(mArticleOnLists, getActivity()));

        /* SwipeRefreshLayout */
        srlArticlesCafe.setOnRefreshListener(this);
        srlArticlesCafe.setColorSchemeColors(Color.RED);


        return view;
    }


    @Override
    public void onRefresh() {
        srlArticlesCafe.setRefreshing(false);
    }
}
