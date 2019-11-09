package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.addcafe.AddCafeActivity;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.interfaces.MyCafeActivityView;
import com.softsqaured.softsquared_daum_cafe.src.search.SearchActivity;

public class MyCafeFragment extends BaseFragment implements MyCafeActivityView {

    private TabLayout tlMyCafe;
    private ViewPager vpMyCafe;
    private ImageView ivSearch;
    private ImageView ivAddCafe;


    private MyCafePagerAdapter mcpAdapter;

    public MyCafeFragment() {
    }

    public static MyCafeFragment newInstance() {
        MyCafeFragment fragment = new MyCafeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_mycafe, container, false);

        /* findViewByID */
        tlMyCafe = view.findViewById(R.id.tab_mycafe);
        vpMyCafe = view.findViewById(R.id.vp_mycafe);
        ivSearch = view.findViewById(R.id.iv_search_mycafe);
        ivAddCafe = view.findViewById(R.id.iv_addcafe_mycafe);


        mcpAdapter = new MyCafePagerAdapter(getChildFragmentManager(), 4);
        vpMyCafe.setAdapter(mcpAdapter);

        /* Viewpager Add on Page Change Listener */
        vpMyCafe.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMyCafe));

        /* TabLayout Add on Tab Selected Listener */
        tlMyCafe.addOnTabSelectedListener(this);

        /* Set On Click Listener */
        ivSearch.setOnClickListener(this);
        ivAddCafe.setOnClickListener(this);


        return view;
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vpMyCafe.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_mycafe:
                startNextActivity(SearchActivity.class);
                break;
            case R.id.iv_addcafe_mycafe:
                startNextActivity(AddCafeActivity.class);
                break;
        }
    }
}
