package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.interfaces.CafeListFragmentView;

public class CafeListFragment extends BaseFragment implements CafeListFragmentView {

    public CafeListFragment() {
    }

    public static CafeListFragment newInstance() {
        CafeListFragment fragment = new CafeListFragment();
        return fragment;
    }

    private RecyclerView rvMyCafeList;
    private RecyclerView rvAllCafeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe_mycafe, container, false);

        /* findViewByID */
        rvMyCafeList = view.findViewById(R.id.rv_list_favorite_cafe_mycafe);
        rvAllCafeList = view.findViewById(R.id.rv_list_mycafes_cafe_mycafe);

        return view;
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
