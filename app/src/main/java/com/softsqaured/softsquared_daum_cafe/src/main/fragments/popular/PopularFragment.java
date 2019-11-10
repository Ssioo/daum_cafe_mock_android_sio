package com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.MainActivity;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.interfaces.PopularFragmentView;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.models.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PopularFragment extends BaseFragment implements PopularFragmentView {

    private Toolbar tbFavorite;
    private DrawerLayout dlPopular;
    private LinearLayout llSelectNowPopular;
    private LinearLayout llSelectWeeklyPopular;
    private LinearLayout llSelectMonthlyPopular;
    private TextView tvToolbarTitleCollapsed;
    private TextView tvToolbarTitleExpanded;
    private AppBarLayout ablPopular;
    private ViewPager vpPopular;
    private TextView tvCurrentHourDrawer;
    private TextView tvCurrentHourDescDrawer;

    private Context mContext;

    int dpUnit;
    int todayHour;

    public PopularFragment() {
    }

    public static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        // Constants - DP to PX
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        dpUnit = (int) metrics.density;

        // Constants - Today
        Calendar today = Calendar.getInstance();
        todayHour = today.get(Calendar.HOUR_OF_DAY);

        /* findViewByID */
        tbFavorite = view.findViewById(R.id.toolbar_popular);
        dlPopular = view.findViewById(R.id.dl_popular);
        llSelectNowPopular = view.findViewById(R.id.drawer_item1_popular);
        llSelectWeeklyPopular = view.findViewById(R.id.drawer_item2_popular);
        llSelectMonthlyPopular = view.findViewById(R.id.drawer_item3_popular);
        ablPopular = view.findViewById(R.id.abl_popular);
        vpPopular = view.findViewById(R.id.vp_popular);
        tvToolbarTitleCollapsed = view.findViewById(R.id.tv_toolbar_title_collapsed_popular);
        tvToolbarTitleExpanded = view.findViewById(R.id.tv_toolbar_title_expanded_popular);
        tvCurrentHourDrawer = view.findViewById(R.id.tv_current_hour_popular_drawer);
        tvCurrentHourDescDrawer = view.findViewById(R.id.tv_current_hour_desc_popular_drawer);

        /* Toolbar */
        setHasOptionsMenu(true);
        ((MainActivity) mContext).setSupportActionBar(tbFavorite);
        ActionBar actionBar = ((MainActivity) mContext).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_popular);

        /* AppBarLayout Add On Offset Change Listener */
        ablPopular.addOnOffsetChangedListener(this);

        // dummy data
        ArrayList<ArrayList<Article>> dummy = new ArrayList<>();
        ArrayList<Article> dummy1 = new ArrayList<>();
        dummy1.add(new Article("과제 신나", "소프트스퀘어드" ,""));
        dummy1.add(new Article("과제 신나", "소프트스퀘어드" ,""));
        dummy1.add(new Article("과제 신나요", "소프트스퀘어드" ,""));
        ArrayList<Article> dummy2 = new ArrayList<>();
        dummy2.add(new Article("과제 신나", "소프트스퀘어드" ,""));
        dummy2.add(new Article("과제 안 신나", "소프트스퀘어드" ,""));
        dummy2.add(new Article("과제 신나", "소프트스퀘어드" ,""));
        dummy2.add(new Article("과제 안 신나", "소프트스퀘어드" ,""));
        ArrayList<Article> dummy3 = new ArrayList<>();
        dummy3.add(new Article("과제 안 신나", "소프트스퀘어드" ,""));
        dummy3.add(new Article("신제 과나", "소프트스퀘어드" ,""));
        dummy3.add(new Article("과나 신제", "소프트스퀘어드" ,""));
        dummy3.add(new Article("과신 제나", "소프트스퀘어드" ,""));
        dummy3.add(new Article("제과 신나", "소프트스퀘어드" ,""));
        dummy3.add(new Article("제과 신나", "소프트스퀘어드" ,""));
        dummy.add(dummy1);
        dummy.add(dummy2);
        dummy.add(dummy3);

        /* ViewPager */
        vpPopular.setAdapter(new PopularArticlesPager(getChildFragmentManager(), dummy));
        vpLayoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        vpLayoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());


        /* Set On Click Listener */
        llSelectMonthlyPopular.setOnClickListener(this);
        llSelectWeeklyPopular.setOnClickListener(this);
        llSelectNowPopular.setOnClickListener(this);

        /* Set TextView */
        tvToolbarTitleExpanded.setText(todayHour + "시, 인기글");
        tvToolbarTitleCollapsed.setText(todayHour + "시, 인기글");
        SimpleDateFormat sdfCurrentHour = new SimpleDateFormat("a HH:mm", Locale.ENGLISH);
        tvCurrentHourDrawer.setText(sdfCurrentHour.format(today.getTime()));
        tvCurrentHourDescDrawer.setText("지금, " + todayHour + "시의 인기글입니다.");

        return view;
    }

    private CoordinatorLayout.LayoutParams vpLayoutParams;

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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_cafe, menu);
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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        // 0 <= i <= -appBar.getTotalScrollRange()
        float collapsedTitleAlpha = 1.0f - Math.abs((i + appBarLayout.getTotalScrollRange()) * 2.5f / (float) appBarLayout.getTotalScrollRange());
        if (collapsedTitleAlpha <= 0.0f)
            collapsedTitleAlpha = 0.0f;
        else if (collapsedTitleAlpha >= 1.0f)
            collapsedTitleAlpha = 1.0f;
        float expandedTitleAlpha = Math.abs((i + appBarLayout.getTotalScrollRange()) * 2.5f / (float) appBarLayout.getTotalScrollRange()) - 1.0f;
        if (expandedTitleAlpha <= 0.0f)
            expandedTitleAlpha = 0.0f;
        else if (expandedTitleAlpha >= 1.0f) {
            expandedTitleAlpha = 1.0f;
        }
        tvToolbarTitleCollapsed.setAlpha(collapsedTitleAlpha);
        tvToolbarTitleExpanded.setAlpha(expandedTitleAlpha);

        // viewPager 마진 동적 변경
        vpLayoutParams.topMargin = (int) (-48 * dpUnit * Math.abs((i + appBarLayout.getTotalScrollRange()) / (float) appBarLayout.getTotalScrollRange()) - 1.0f);
        vpPopular.setLayoutParams(vpLayoutParams);
    }
}
