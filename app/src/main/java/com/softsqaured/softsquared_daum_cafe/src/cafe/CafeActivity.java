package com.softsqaured.softsquared_daum_cafe.src.cafe;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseActivity;
import com.softsqaured.softsquared_daum_cafe.src.cafe.interfaces.CafeActivityView;
import com.softsqaured.softsquared_daum_cafe.src.mypage_cafe.MyPageActivity;
import com.softsqaured.softsquared_daum_cafe.src.mysetting_cafe.MySettingActivity;

public class CafeActivity extends BaseActivity implements CafeActivityView {

    private DrawerLayout dlCafe;
    private Toolbar tbCafe;
    private TabLayout tlCafe;
    private ViewPager vpCafe;
    private ImageView ivCloseDrawer;
    private AppCompatImageButton ibtnSetting;
    private LinearLayout llProfileDrawer;
    private TextView tvHome;
    private TextView tvAdmin;
    private TextView tvChat;
    private TextView tvSearch;
    private AppCompatImageButton ivCloseItem1;
    private AppCompatImageButton ivCloseItem2;
    private AppCompatImageButton ivCloseItem3;
    private RecyclerView rvBoardList1;
    private RecyclerView rvBoardList2;
    private RecyclerView rvBoardList3;
    private SwipeRefreshLayout srlBoardListDrawer;
    private AppBarLayout ablCafe;
    private ImageView ivThumbnailCafe;
    private ImageView ivStarCafe;
    private ImageView ivInfoCafe;
    private ImageView ivNotiCafe;
    private TextView tvCafeTitle;
    private LinearLayout llToolbarCafe;
    private TextView tvToolbarTitle;

    private boolean DRAWER_ITEM1_OPENED = true;
    private boolean DRAWER_ITEM2_OPENED = true;
    private boolean DRAWER_ITEM3_OPENED = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        /* findViewByID */
        dlCafe = findViewById(R.id.dl_cafe);
        tbCafe = findViewById(R.id.toolbar_cafe);
        tlCafe = findViewById(R.id.tab_cafe);
        vpCafe = findViewById(R.id.vp_cafe);
        ivCloseDrawer = findViewById(R.id.iv_close_cafe_drawer);
        ibtnSetting = findViewById(R.id.ibtn_setting_cafe_drawer);
        llProfileDrawer = findViewById(R.id.ll_profile_cafe_drawer);
        tvHome = findViewById(R.id.tv_home_cafe_drawer);
        tvAdmin = findViewById(R.id.tv_admin_cafe_drawer);
        tvChat = findViewById(R.id.tv_chat_cafe_drawer);
        tvSearch = findViewById(R.id.tv_search_cafe_drawer);
        ivCloseItem1 = findViewById(R.id.ibtn_close_boardlist1_cafe_drawer);
        ivCloseItem2 = findViewById(R.id.ibtn_close_boardlist2_cafe_drawer);
        ivCloseItem3 = findViewById(R.id.ibtn_close_boardlist3_cafe_drawer);
        rvBoardList1 = findViewById(R.id.rv_boardlist1_cafe);
        rvBoardList2 = findViewById(R.id.rv_boardlist2_cafe);
        rvBoardList3 = findViewById(R.id.rv_boardlist3_cafe);
        srlBoardListDrawer = findViewById(R.id.srl_drawer_cafe_drawer);
        ablCafe = findViewById(R.id.appbar_cafe);
        ivThumbnailCafe = findViewById(R.id.iv_cafe_thumbnail_cafe);
        ivStarCafe = findViewById(R.id.iv_star_cafe);
        ivInfoCafe = findViewById(R.id.iv_info_cafe);
        ivNotiCafe = findViewById(R.id.iv_noti_cafe);
        tvCafeTitle = findViewById(R.id.tv_cafe_title_cafe);
        llToolbarCafe = findViewById(R.id.ll_toolbar_cafe);

        /* Toolbar */
        tvToolbarTitle = new TextView(this);
        tvToolbarTitle.setText(tvCafeTitle.getText().toString());
        tvToolbarTitle.setTextColor(Color.WHITE);
        tvToolbarTitle.setTextSize(16);
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        tvToolbarTitle.setLayoutParams(params);
        tbCafe.addView(tvToolbarTitle);
        setSupportActionBar(tbCafe);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left);

        /* AppbarLayout Add on Offset Change Listener */
        ablCafe.addOnOffsetChangedListener(this);

        /* Set on Click Listener */
        ivCloseDrawer.setOnClickListener(this);
        ibtnSetting.setOnClickListener(this);
        llProfileDrawer.setOnClickListener(this);
        tvHome.setOnClickListener(this);
        tvAdmin.setOnClickListener(this);
        tvChat.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        ivCloseItem1.setOnClickListener(this);
        ivCloseItem2.setOnClickListener(this);
        ivCloseItem3.setOnClickListener(this);

        /* TabLayout */
        tlCafe.addOnTabSelectedListener(this);

        /* ViewPager */
        vpCafe.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlCafe));
        vpCafe.setAdapter(new CafeBoardPagerAdapter(getSupportFragmentManager(), 2));

        /* SwipeRefreshLayout - Drawer */
        srlBoardListDrawer.setOnRefreshListener(this);

    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_cafe_drawer:
                break;
            case R.id.ibtn_setting_cafe_drawer:
                // Setting Activity 이동
                startNextActivity(MySettingActivity.class);
                break;
            case R.id.ll_profile_cafe_drawer:
                // Profile Activity 이동
                startNextActivity(MyPageActivity.class);
                break;
            case R.id.tv_home_cafe_drawer:
                break;
            case R.id.tv_admin_cafe_drawer:
                showToast("미구현 기능입니다");
                break;
            case R.id.tv_chat_cafe_drawer:
                showToast("미구현 기능입니다");
                break;
            case R.id.tv_search_cafe_drawer:
                showToast("미구현 기능입니다");
                break;
            case R.id.ibtn_close_boardlist1_cafe_drawer:
                if (DRAWER_ITEM1_OPENED) {
                    ivCloseItem1.setImageResource(R.drawable.ic_expand_more);
                    rvBoardList1.setVisibility(View.GONE);
                } else {
                    ivCloseItem1.setImageResource(R.drawable.ic_expand_less);
                    rvBoardList1.setVisibility(View.VISIBLE);
                }
                DRAWER_ITEM1_OPENED = !DRAWER_ITEM1_OPENED;
                return;
            case R.id.ibtn_close_boardlist2_cafe_drawer:
                if (DRAWER_ITEM2_OPENED) {
                    ivCloseItem2.setImageResource(R.drawable.ic_expand_more);
                    rvBoardList2.setVisibility(View.GONE);
                } else {
                    ivCloseItem2.setImageResource(R.drawable.ic_expand_less);
                    rvBoardList2.setVisibility(View.VISIBLE);
                }
                DRAWER_ITEM2_OPENED = !DRAWER_ITEM2_OPENED;
                return;
            case R.id.ibtn_close_boardlist3_cafe_drawer:
                if (DRAWER_ITEM3_OPENED) {
                    ivCloseItem3.setImageResource(R.drawable.ic_expand_more);
                    rvBoardList3.setVisibility(View.GONE);
                } else {
                    ivCloseItem3.setImageResource(R.drawable.ic_expand_less);
                    rvBoardList3.setVisibility(View.VISIBLE);
                }
                DRAWER_ITEM3_OPENED = !DRAWER_ITEM3_OPENED;
                return;
        }
        dlCafe.closeDrawers();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vpCafe.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_cafe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.tb_drawer_cafe:
                if (!dlCafe.isDrawerOpen(GravityCompat.END)) {
                    dlCafe.openDrawer(GravityCompat.END);
                }
        }
        return true;
    }

    @Override
    public void onRefresh() {
        srlBoardListDrawer.setRefreshing(false);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i + appBarLayout.getTotalScrollRange() == 0) {
            // TOTALLY COLLAPSED
            llToolbarCafe.setVisibility(View.INVISIBLE);
            tvToolbarTitle.setVisibility(View.VISIBLE);
        } else {
            llToolbarCafe.setVisibility(View.VISIBLE);
            tvToolbarTitle.setVisibility(View.INVISIBLE);
        }
    }
}
