package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist;

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

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.adpater.CafeListMyCafeAdapter;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.interfaces.CafeListFragmentView;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model.CafeListItem;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model.CafeListResponse;

import java.util.ArrayList;

public class CafeListFragment extends BaseFragment implements CafeListFragmentView {

    private RecyclerView rvMyCafeList;
    private RecyclerView rvAllCafeList;
    private SwipeRefreshLayout srlMyCafeCafe;
    private TextView tvCountMyCafe;
    private TextView tvCountAllCafe;

    private ArrayList<CafeListResponse.Result> cafeLists = new ArrayList<>();

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

        /* Get Cafe Lists From Server */
        //getCafeList();

        /* findViewByID */
        rvMyCafeList = view.findViewById(R.id.rv_list_favorite_mycafe_cafe);
        rvAllCafeList = view.findViewById(R.id.rv_list_mycafes_mycafe_cafe);
        srlMyCafeCafe = view.findViewById(R.id.srl_mycafe_cafe);
        tvCountAllCafe = view.findViewById(R.id.tv_count_allcafes_cafe_mycafe);
        tvCountMyCafe = view.findViewById(R.id.tv_count_mycafes_cafe_mycafe);

        /* SwipeRefreshLayout */
        srlMyCafeCafe.setOnRefreshListener(this);

        /* RecyclerView - My Cafe */
        rvMyCafeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAllCafeList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Dummy Data
        ArrayList<CafeListItem> dummy = new ArrayList<>();
        dummy.add(new CafeListItem("", "소프트스퀘어드", "2019.11.02", true));
        dummy.add(new CafeListItem("", "소프트스퀘어드2", "2019.11.03", true));
        CafeListMyCafeAdapter clmAdapter = new CafeListMyCafeAdapter(dummy, getActivity());
        rvMyCafeList.setAdapter(clmAdapter);

        // Dummy Data
        ArrayList<CafeListItem> dummy2 = new ArrayList<>();
        dummy2.add(new CafeListItem("", "소프트스퀘어드3", "2019.11.04", false));
        dummy2.add(new CafeListItem("", "소프트스퀘어드4", "2019.11.05", false));
        dummy2.add(new CafeListItem("", "소프트스퀘어드5", "2019.11.01", false));
        rvAllCafeList.setAdapter(new CafeListMyCafeAdapter(dummy2, getActivity()));

        /* Set Text */
        tvCountMyCafe.setText(String.valueOf(dummy.size()));
        tvCountAllCafe.setText(String.valueOf(dummy2.size()));

        return view;
    }

    @Override
    public void validateSuccess(ArrayList<CafeListResponse.Result> results) {
        hideProgressDialog();

        // result를 순회하면서 favorited 여부를 판단해서 각기 다른 arraylist에 저장
        // 이후 즐겨찾기 시 notify item change로 뷰를 구성하고 서버에 정보 post.

        // Dummy Data
        ArrayList<CafeListItem> dummy = new ArrayList<>();
        dummy.add(new CafeListItem("", "소프트스퀘어드", "2019.11.02", true));
        dummy.add(new CafeListItem("", "소프트스퀘어드2", "2019.11.03", true));
        CafeListMyCafeAdapter clmAdapter = new CafeListMyCafeAdapter(dummy, getActivity());
        rvMyCafeList.setAdapter(clmAdapter);

        // Dummy Data
        ArrayList<CafeListItem> dummy2 = new ArrayList<>();
        dummy2.add(new CafeListItem("", "소프트스퀘어드3", "2019.11.04", false));
        dummy2.add(new CafeListItem("", "소프트스퀘어드4", "2019.11.05", false));
        dummy2.add(new CafeListItem("", "소프트스퀘어드5", "2019.11.01", false));
        rvAllCafeList.setAdapter(new CafeListMyCafeAdapter(dummy2, getActivity()));

        /* Set Text */
        tvCountMyCafe.setText(String.valueOf(dummy.size()));
        tvCountAllCafe.setText(String.valueOf(dummy2.size()));
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast((message == null) ? getString(R.string.network_error) : message);
    }

    public void getCafeList() {
        showProgressDialog();
        final CafeListService cafeListService = new CafeListService(this);
        cafeListService.getCafeList();
    }

    @Override
    public void onRefresh() {
        //getCafeList();
        srlMyCafeCafe.setRefreshing(false);
    }
}
