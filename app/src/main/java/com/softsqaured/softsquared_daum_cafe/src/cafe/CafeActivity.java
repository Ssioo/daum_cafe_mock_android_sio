package com.softsqaured.softsquared_daum_cafe.src.cafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseActivity;
import com.softsqaured.softsquared_daum_cafe.src.cafe.interfaces.CafeActivityView;

public class CafeActivity extends BaseActivity implements CafeActivityView, View.OnClickListener, TabLayout.OnTabSelectedListener{

    private DrawerLayout dlCafe;
    private Toolbar tbCafe;
    private TabLayout tlCafe;
    private ViewPager vpCafe;
    private ImageView ivCloseDrawer;

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

        /* Toolbar */
        setSupportActionBar(tbCafe);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cafe Title");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left);

        /* Set on Click Listener */
        ivCloseDrawer.setOnClickListener(this);

        /* TabLayout */
        tlCafe.addOnTabSelectedListener(this);

        /* ViewPager */
        vpCafe.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlCafe));
        vpCafe.setAdapter(new CafeBoardPagerAdapter(getSupportFragmentManager(), 2));
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
                if (dlCafe.isDrawerOpen(GravityCompat.END)) {
                    dlCafe.closeDrawers();
                }
                break;
        }
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
}
