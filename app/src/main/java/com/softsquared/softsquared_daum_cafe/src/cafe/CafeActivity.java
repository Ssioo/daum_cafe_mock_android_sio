package com.softsquared.softsquared_daum_cafe.src.cafe;

import android.content.Context;
import android.content.Intent;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.cafe.adpater.CafeBoardPagerAdapter;
import com.softsquared.softsquared_daum_cafe.src.cafe.adpater.CafeCategoryListAdapter;
import com.softsquared.softsquared_daum_cafe.src.cafe.interfaces.CafeActivityView;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.Category;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CategoryResponse;
import com.softsquared.softsquared_daum_cafe.src.cafe.mypage.MyPageActivity;
import com.softsquared.softsquared_daum_cafe.src.cafe.mysetting.MySettingActivity;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.WriteActivity;
import com.softsquared.softsquared_daum_cafe.src.chat.ChatActivity;
import com.softsquared.softsquared_daum_cafe.src.search.SearchActivity;

import java.util.ArrayList;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_EMAIL;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class CafeActivity extends BaseActivity implements CafeActivityView {

    public static final int REQUEST_TO_WRITE = 1000;

    private DrawerLayout dlCafe;
    private AppBarLayout ablCafe;
    private Toolbar tbCafe;
    private TextView tvToolbarTitle;
    private ImageView ivThumbnailCafe;
    private TextView tvCafeTitle;
    private ImageView ivStarCafe;
    private ImageView ivInfoCafe;
    private ImageView ivNotiCafe;
    private LinearLayout llToolbarCafe;
    private ImageView ivToolbarExpand;
    private LinearLayout llToolbarContents;
    private TabLayout tlCafe;
    private ViewPager vpCafe;
    private TextView tvWrite;
    private TextView tvSearchBottom;
    private TextView tvRefresh;
    private ImageView ivShowNav;
    private ImageView ivCloseDrawer;
    private TextView tvHome;
    private TextView tvAdmin;
    private TextView tvChat;
    private TextView tvSearch;
    private SwipeRefreshLayout srlBoardListDrawer;
    private AppCompatImageButton ivCloseItem1;
    private AppCompatImageButton ivCloseItem2;
    private AppCompatImageButton ivCloseItem3;
    private RecyclerView rvBoardList1;
    private RecyclerView rvBoardList2;
    private RecyclerView rvBoardList3;
    private LinearLayout llProfileDrawer;
    private TextView tvUserNameDrawer;
    private AppCompatImageButton ibtnSetting;

    private boolean DRAWER_ITEM1_OPENED = true;
    private boolean DRAWER_ITEM2_OPENED = true;
    private boolean DRAWER_ITEM3_OPENED = true;

    ArrayList<ArrayList<CafeResponse.Result>> articleList = new ArrayList<>();

    private String cafeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        /* Get Intent */
        Intent intent = getIntent();
        cafeName = intent.getStringExtra("cafeName");

        /* Get Articles From Server */
        getArticles(cafeName);
        getCategories();

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
        tvWrite = findViewById(R.id.tv_write_cafe);
        tvSearchBottom = findViewById(R.id.tv_search_cafe);
        tvRefresh = findViewById(R.id.tv_refresh_cafe);
        ivShowNav = findViewById(R.id.iv_show_nav_cafe);
        tvUserNameDrawer = findViewById(R.id.tv_username_cafe_drawer);

        /* Toolbar */
        llToolbarContents = new LinearLayout(this);
        addViewsForToolbar(this, llToolbarContents, tbCafe);
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
        tbCafe.setOnClickListener(this);
        tvWrite.setOnClickListener(this);
        tvSearchBottom.setOnClickListener(this);
        tvRefresh.setOnClickListener(this);
        ivShowNav.setOnClickListener(this);

        /* TabLayout */
        tlCafe.addOnTabSelectedListener(this);

        /* ViewPager */
        vpCafe.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlCafe));

        /* SwipeRefreshLayout - Drawer */
        srlBoardListDrawer.setOnRefreshListener(this);

        /* Init View */
        if (sSharedPreferences.getBoolean(USER_LOGINNED, false))
            tvUserNameDrawer.setText(sSharedPreferences.getString(USER_EMAIL, ""));
        else
            tvUserNameDrawer.setText("로그인해주세요.");
        tvCafeTitle.setText(cafeName);

    }

    private void addViewsForToolbar(Context context, LinearLayout llToolbarContainer, Toolbar toolbar) {
        // Container
        llToolbarContainer.setOrientation(LinearLayout.HORIZONTAL);
        Toolbar.LayoutParams llParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        llParams.gravity = Gravity.CENTER;
        llToolbarContainer.setLayoutParams(llParams);
        // Title
        TextView tvTitle = new TextView(context);
        tvTitle.setText(cafeName);
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(17);
        tvTitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        tvTitle.setLayoutParams(params);
        llToolbarContainer.addView(tvTitle);
        // Icon
        ImageView ivExpand = new ImageView(context);
        ivExpand.setImageResource(R.drawable.ic_expane_more_circle);
        ivExpand.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ivExpand.setAdjustViewBounds(true);
        LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ivParams.gravity = Gravity.CENTER;
        ivParams.setMargins(4 * dpUnit, 16 * dpUnit, 0, 16 * dpUnit);
        ivExpand.setLayoutParams(ivParams);
        llToolbarContainer.addView(ivExpand);

        toolbar.addView(llToolbarContainer);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_TO_WRITE) {
            getArticles(cafeName);
        }
    }

    @Override
    public void validateSuccess(ArrayList<CafeResponse.Result> results) {
        hideProgressDialog();
        articleList.clear();
        articleList.add(results);
        articleList.add(results);
        vpCafe.setAdapter(new CafeBoardPagerAdapter(getSupportFragmentManager(), 2, articleList));
    }

    @Override
    public void validateCategorySuccess(ArrayList<CategoryResponse.Result> results) {
        // Category 구성
        hideProgressDialog();
        ArrayList<Category> categories = new ArrayList<>();
        for (CategoryResponse.Result result : results) {
            categories.add(new Category(result.getCategoryName(), "", true));
        }
        rvBoardList2.setAdapter(new CafeCategoryListAdapter(categories, this));
        rvBoardList3.setAdapter(new CafeCategoryListAdapter(categories, this));
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast((message == null) ? getString(R.string.network_error) : message);
    }

    public void getArticles(String cafeName) {
        showProgressDialog();
        final CafeService cafeService = new CafeService(this);
        cafeService.getArticles(cafeName);
    }

    public void getCategories() {
        showProgressDialog();
        final CafeService cafeService = new CafeService(this);
        cafeService.getCategories();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_cafe:
                ablCafe.setExpanded(true, true);
                break;
            case R.id.iv_show_nav_cafe:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.tv_search_cafe:
                // Search Activity로 이동
                Intent intent = new Intent(CafeActivity.this, SearchActivity.class);
                intent.putExtra("MODE_ADVERTISE", false);
                startActivity(intent);
                break;
            case R.id.tv_write_cafe:
                // Write Activity로 이동
                Intent intent1 = new Intent(CafeActivity.this, WriteActivity.class);
                intent1.putExtra("activityMode", "CREATE");
                intent1.putExtra("cafeName", cafeName);
                startActivityForResult(intent1, REQUEST_TO_WRITE);
                break;
            case R.id.tv_refresh_cafe:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.ibtn_setting_cafe_drawer:
                // Setting Activity 이동
                if (sSharedPreferences.getBoolean(USER_LOGINNED, false))
                    startNextActivity(MySettingActivity.class);
                else
                    mLoginAlert.show();
                break;
            case R.id.ll_profile_cafe_drawer:
                // Profile Activity 이동
                if (sSharedPreferences.getBoolean(USER_LOGINNED, false))
                    startNextActivity(MyPageActivity.class);
                else
                    mLoginAlert.show();
                break;
            case R.id.tv_home_cafe_drawer:
                break;
            case R.id.tv_admin_cafe_drawer:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.tv_chat_cafe_drawer:
                // Chat Activity로 이동
                if (sSharedPreferences.getBoolean(USER_LOGINNED, false))
                    startNextActivity(ChatActivity.class);
                else
                    mLoginAlert.show();
                break;
            case R.id.tv_search_cafe_drawer:
                showToast(getString(R.string.nofunction));
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
            case R.id.iv_close_cafe_drawer:
                break;
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
    public void onBackPressed() {
        if (dlCafe.isDrawerOpen(GravityCompat.END)) {
            dlCafe.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onRefresh() {
        // Category List Refresh.
        getCategories();
        srlBoardListDrawer.setRefreshing(false);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i + appBarLayout.getTotalScrollRange() == 0) {
            // TOTALLY COLLAPSED
            llToolbarCafe.setVisibility(View.INVISIBLE);
            llToolbarContents.setVisibility(View.VISIBLE);
        } else {
            llToolbarCafe.setVisibility(View.VISIBLE);
            llToolbarContents.setVisibility(View.INVISIBLE);
        }
    }
}
