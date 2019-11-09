package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.interfaces.CafeListFragmentView;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model.CafeListItem;

import java.util.ArrayList;

public class CafeListFragment extends BaseFragment implements CafeListFragmentView {

    private RecyclerView rvMyCafeList;
    private RecyclerView rvAllCafeList;
    private SwipeRefreshLayout srlMyCafeCafe;
    private TextView tvCountMyCafe;
    private TextView tvCountAllCafe;

    public CafeListFragment() {
    }

    public static CafeListFragment newInstance() {
        CafeListFragment fragment = new CafeListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycafe_cafe, container, false);

        /* findViewByID */
        rvMyCafeList = view.findViewById(R.id.rv_list_favorite_mycafe_cafe);
        rvAllCafeList = view.findViewById(R.id.rv_list_mycafes_mycafe_cafe);
        srlMyCafeCafe = view.findViewById(R.id.srl_mycafe_cafe);
        tvCountAllCafe = view.findViewById(R.id.tv_count_allcafes_cafe_mycafe);
        tvCountMyCafe = view.findViewById(R.id.tv_count_mycafes_cafe_mycafe);

        /* SwipeRefreshLayout */
        srlMyCafeCafe.setOnRefreshListener(this);

        /* RecyclerView - My Cafe */
        // Dummy Data
        ArrayList<CafeListItem> dummy = new ArrayList<>();
        dummy.add(new CafeListItem("", "소프트스퀘어드", "2019.11.02", true));
        dummy.add(new CafeListItem("", "소프트스퀘어드2", "2019.11.03", true));
        rvMyCafeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMyCafeList.setAdapter(new CafeListMyCafeAdapter(dummy, getActivity()));

        /* RecyclerView - All Cafe */
        // Dummy Data
        ArrayList<CafeListItem> dummy2 = new ArrayList<>();
        dummy2.add(new CafeListItem("", "소프트스퀘어드3", "2019.11.04", false));
        dummy2.add(new CafeListItem("", "소프트스퀘어드4", "2019.11.05", false));
        dummy2.add(new CafeListItem("", "소프트스퀘어드5", "2019.11.01", false));
        rvAllCafeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAllCafeList.setAdapter(new CafeListMyCafeAdapter(dummy2, getActivity()));

        /* Set Text */
        tvCountMyCafe.setText(String.valueOf(dummy.size()));
        tvCountAllCafe.setText(String.valueOf(dummy2.size()));

        return view;
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void onRefresh() {
        srlMyCafeCafe.setRefreshing(false);
    }
}
