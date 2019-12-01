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
import com.softsquared.softsquared_daum_cafe.src.cafe.CafeActivity;
import com.softsquared.softsquared_daum_cafe.src.cafe.fragments.article.adapter.ArticleOnListListAdapter;
import com.softsquared.softsquared_daum_cafe.src.cafe.fragments.article.interfaces.ArticlesBoardFragmentView;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;
import com.softsquared.softsquared_daum_cafe.src.common.util.RecyclerViewDecoration;

import java.util.ArrayList;

public class ArticlesBoardFragment extends BaseFragment implements ArticlesBoardFragmentView {

    private RecyclerView rvArticlesCafe;
    private SwipeRefreshLayout srlArticlesCafe;

    private ArticleOnListListAdapter articleOnListListAdapter;
    private String mCafeName;

    private ArrayList<CafeResponse.Result> mArticleOnLists;

    public ArticlesBoardFragment(ArrayList<CafeResponse.Result> results, String cafeName) {
        this.mArticleOnLists = results;
        mCafeName = cafeName;
    }

    public static ArticlesBoardFragment newInstance(ArrayList<CafeResponse.Result> results, String cafeName) {
        ArticlesBoardFragment fragment = new ArticlesBoardFragment(results, cafeName);
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
        articleOnListListAdapter = new ArticleOnListListAdapter(mArticleOnLists, getActivity());
        rvArticlesCafe.setAdapter(articleOnListListAdapter);
        rvArticlesCafe.addItemDecoration(new RecyclerViewDecoration(1, 0));

        /* SwipeRefreshLayout */
        srlArticlesCafe.setOnRefreshListener(this);
        srlArticlesCafe.setColorSchemeColors(Color.RED);


        return view;
    }


    @Override
    public void onRefresh() {
        // Article List Refresh.
        CafeActivity parent = (CafeActivity) getActivity();
        if (parent != null) {
            parent.getArticles(mCafeName);
        }
        srlArticlesCafe.setRefreshing(false);
    }
}
