package com.softsquared.softsquared_daum_cafe.src.cafe.fragments.allarticle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.ArticleOnList;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.ArticleOnListListAdapter;

import java.util.ArrayList;

public class AllArticlesBoard extends BaseFragment {

    private RecyclerView rvArticlesAllArticleBoard;

    public static AllArticlesBoard newInstance() {
        AllArticlesBoard fragment = new AllArticlesBoard();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe_allarticleboard, container, false);

        /* findViewByID */
        rvArticlesAllArticleBoard = view.findViewById(R.id.rv_articlelist_cafe_allarticleboard);

        /* RecyclerView */
        rvArticlesAllArticleBoard.setLayoutManager(new LinearLayoutManager(getActivity()));

        // dummy Data
        ArrayList<ArticleOnList> dummy = new ArrayList<>();
        dummy.add(new ArticleOnList("테스트1", "시오", "2019.11.02", "2", "2", "자유게시판"));
        dummy.add(new ArticleOnList("테스트2", "시오", "2019.11.02", "2", "0", "자유게시판"));
        dummy.add(new ArticleOnList("테스트3", "보유미", "2019.11.01", "5", "12", "자유게시판"));
        dummy.add(new ArticleOnList("테스트4", "보유미", "2019.11.01", "10", "2", "자유게시판"));
        dummy.add(new ArticleOnList("테스트5", "시오", "2019.11.01", "30", "27", "자유게시판"));
        dummy.add(new ArticleOnList("테스트6", "시오", "2019.11.02", "2", "2", "자유게시판"));
        dummy.add(new ArticleOnList("테스트7", "시오", "2019.11.02", "2", "0", "자유게시판"));
        dummy.add(new ArticleOnList("테스트8", "보유미", "2019.11.01", "5", "12", "자유게시판"));
        dummy.add(new ArticleOnList("페이징테스트9", "보유미", "2019.11.01", "10", "2", "자유게시판"));
        dummy.add(new ArticleOnList("페이징테스트10", "시오", "2019.11.01", "30", "27", "자유게시판"));
        dummy.add(new ArticleOnList("페이징테스트11", "시오", "2019.11.01", "38", "27", "자유게시판"));

        rvArticlesAllArticleBoard.setAdapter(new ArticleOnListListAdapter(dummy, getActivity()));

        return view;
    }
}
