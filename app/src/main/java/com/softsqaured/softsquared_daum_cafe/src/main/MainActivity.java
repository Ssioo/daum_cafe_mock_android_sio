package com.softsqaured.softsquared_daum_cafe.src.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseActivity;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.favorite.FavoriteFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.home.HomeFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.notification.NotificationFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.interfaces.MainActivityView;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.MyCafeFragment;

public class MainActivity extends BaseActivity implements MainActivityView, BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* findViewByID */
        bottomNavMain = findViewById(R.id.bottomnav_main);


        /* Init View */
        getSupportFragmentManager().beginTransaction().add(R.id.frame_main, MyCafeFragment.newInstance(), "MYCAFE").commit();

        /* Bottom Navigation View */
        bottomNavMain.setOnNavigationItemSelectedListener(this);
        bottomNavMain.setSelectedItemId(R.id.nav_mycafe);

    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, HomeFragment.newInstance(), "HOME").commit();
                return true;
            case R.id.nav_mycafe:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, MyCafeFragment.newInstance(), "MYCAFE").commit();
                return true;
            case R.id.nav_favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, FavoriteFragment.newInstance(), "FAVORITE").commit();
                return true;
            case R.id.nav_notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, NotificationFragment.newInstance(), "NOTIFICATION").commit();
                return true;
            case R.id.nav_setting:
                // fragmentManager.beginTransaction().replace(R.id.frame_main, notificationFragment).commitAllowingStateLoss();
                return true;
        }
        return false;
    }
}
