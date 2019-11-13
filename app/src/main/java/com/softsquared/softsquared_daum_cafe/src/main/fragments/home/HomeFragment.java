package com.softsquared.softsquared_daum_cafe.src.main.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.home.interfaces.HomeFragmentView;
import com.softsquared.softsquared_daum_cafe.src.search.SearchActivity;

public class HomeFragment extends BaseFragment implements HomeFragmentView {

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private ImageView ivSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /* findVIewByID */
        ivSearch = view.findViewById(R.id.iv_search_home);

        /* Set on Click Listener */
        ivSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_home:
                startNextActivity(SearchActivity.class);
                break;
        }
    }
}
