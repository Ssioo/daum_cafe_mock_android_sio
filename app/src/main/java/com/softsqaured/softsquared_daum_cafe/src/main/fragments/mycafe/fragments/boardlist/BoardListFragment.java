package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.boardlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.boardlist.interfaces.BoardListFragmentView;

public class BoardListFragment extends BaseFragment implements BoardListFragmentView, SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout srlMyCafeBoard;
    private RecyclerView rvMyCafeBoard;

    public BoardListFragment() {
    }

    public static BoardListFragment newInstance() {
        BoardListFragment fragment = new BoardListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycafe_board, container, false);

        /* findViewByID */
        srlMyCafeBoard = view.findViewById(R.id.srl_mycafe_board);
        rvMyCafeBoard = view.findViewById(R.id.rv_list_favorite_mycafe_board);

        /* SwipeRefreshLayout */
        srlMyCafeBoard.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onRefresh() {
        srlMyCafeBoard.setRefreshing(false);
    }
}
