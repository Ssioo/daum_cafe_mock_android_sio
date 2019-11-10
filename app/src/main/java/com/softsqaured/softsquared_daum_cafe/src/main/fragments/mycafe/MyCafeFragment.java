package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.addcafe.AddCafeActivity;
import com.softsqaured.softsquared_daum_cafe.src.main.MainActivity;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.interfaces.MyCafeFragmentView;
import com.softsqaured.softsquared_daum_cafe.src.search.SearchActivity;

public class MyCafeFragment extends BaseFragment implements MyCafeFragmentView {

    private TabLayout tlMyCafe;
    private ViewPager vpMyCafe;
    private Toolbar tbMyCafe;


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
        tbMyCafe = view.findViewById(R.id.toolbar_mycafe);

        mcpAdapter = new MyCafePagerAdapter(getChildFragmentManager(), 4);
        vpMyCafe.setAdapter(mcpAdapter);

        /* Toolbar */
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setSupportActionBar(tbMyCafe);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_add_cafe);


        /* Viewpager Add on Page Change Listener */
        vpMyCafe.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMyCafe));

        /* TabLayout Add on Tab Selected Listener */
        tlMyCafe.addOnTabSelectedListener(this);


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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_mycafe, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startNextActivity(AddCafeActivity.class);
                break;
            case R.id.tb_search_mycafe:
                startNextActivity(SearchActivity.class);
                break;
        }
        return true;
    }
}
