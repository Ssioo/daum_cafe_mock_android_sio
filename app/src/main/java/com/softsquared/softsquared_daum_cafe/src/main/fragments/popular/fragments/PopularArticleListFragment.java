package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.fragments;

import android.content.Context;
import android.graphics.Color;
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
import com.softsquared.softsquared_daum_cafe.src.common.util.RecyclerViewDecoration;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.PopularFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.PopularService;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.fragments.adapter.PopularArticleListAdapter;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.fragments.interfaces.PopularArticleListFragmentView;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.Article;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.PopularResponse;

import java.util.ArrayList;

public class PopularArticleListFragment extends BaseFragment implements PopularArticleListFragmentView {

    private SwipeRefreshLayout srlPopular;
    private RecyclerView rvPopular;

    private ArrayList<PopularResponse.Result> articlesPopular;
    private PopularArticleListAdapter palAdapter;
    private int viewType;
    private Context mContext;

    public PopularArticleListFragment(ArrayList<PopularResponse.Result> articlesPopular, int viewType) {
        this.articlesPopular = articlesPopular;
        this.viewType = viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public static PopularArticleListFragment newInstanceNow(ArrayList<PopularResponse.Result> articlesPopular, int viewType) {
        return new PopularArticleListFragment(articlesPopular, viewType);
    }
    public static PopularArticleListFragment newInstanceWeek(ArrayList<PopularResponse.Result> articlesPopular, int viewType) {
        return new PopularArticleListFragment(articlesPopular, viewType);
    }
    public static PopularArticleListFragment newInstanceMonth(ArrayList<PopularResponse.Result> articlesPopular, int viewType) {
        return new PopularArticleListFragment(articlesPopular, viewType);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
        rvPopular.setAdapter(palAdapter);
        if (viewType == 0) {
            rvPopular.addItemDecoration(new RecyclerViewDecoration(30, 30));
        } else if (viewType == 1) {
            rvPopular.addItemDecoration(new RecyclerViewDecoration(1, 30));
        }

        /* SwipeRefreshLayout On Refresh Listener */
        srlPopular.setOnRefreshListener(this);
        srlPopular.setColorSchemeColors(Color.RED);

        return view;
    }

    @Override
    public void onRefresh() {
        // 새로고침 여기서 구현. -> PopularFragmentView에 callback 추가?
        ((PopularFragment) getParentFragment()).getArticles();
        srlPopular.setRefreshing(false);
    }
}
