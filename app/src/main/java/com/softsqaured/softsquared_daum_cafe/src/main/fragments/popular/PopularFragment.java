package com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.MainActivity;

public class PopularFragment extends BaseFragment implements View.OnClickListener {

    private Toolbar tbFavorite;
    private DrawerLayout dlPopular;
    private LinearLayout llSelectNowPopular;
    private LinearLayout llSelectWeeklyPopular;
    private LinearLayout llSelectMonthlyPopular;

    public PopularFragment() {
    }

    public static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        /* findViewByID */
        tbFavorite = view.findViewById(R.id.toolbar_popular);
        dlPopular = view.findViewById(R.id.dl_popular);
        llSelectNowPopular = view.findViewById(R.id.drawer_item1_popular);
        llSelectWeeklyPopular = view.findViewById(R.id.drawer_item2_popular);
        llSelectMonthlyPopular = view.findViewById(R.id.drawer_item3_popular);

        /* Toolbar */
        ((MainActivity) getActivity()).setSupportActionBar(tbFavorite);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("2시 인기글");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_popular);

        /* Set On Click Listener */
        llSelectMonthlyPopular.setOnClickListener(this);
        llSelectWeeklyPopular.setOnClickListener(this);
        llSelectNowPopular.setOnClickListener(this);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dlPopular.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawer_item1_popular:
                dlPopular.closeDrawers();
                break;
            case R.id.drawer_item2_popular:
                dlPopular.closeDrawers();
                break;
            case R.id.drawer_item3_popular:
                dlPopular.closeDrawers();
                break;
        }
    }
}
