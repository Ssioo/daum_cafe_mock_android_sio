package com.softsquared.softsquared_daum_cafe.src.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.home.HomeFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.MyCafeFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.notification.NotificationFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.PopularFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.setting.SettingFragment;
import com.softsquared.softsquared_daum_cafe.src.main.interfaces.MainActivityView;
import com.softsquared.softsquared_daum_cafe.src.signselect.SignSelectActivity;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.isUserLogin;

public class MainActivity extends BaseActivity implements MainActivityView {

    private BottomNavigationView bottomNavMain;

    private AlertDialog mLoginAlert;

    private boolean FIRST_LOADING = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* findViewByID */
        bottomNavMain = findViewById(R.id.bottomnav_main);


        /* Init View */
        getSupportFragmentManager().beginTransaction().add(R.id.frame_main, PopularFragment.newInstance(), "POPULAR").commit();

        /* AlertDialog Init */
        mLoginAlert = new AlertDialog.Builder(this).setMessage("로그인 후 이용할 수 있습니다.\n로그인 하시겠습니까?")
                .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startNextActivity(SignSelectActivity.class);
                        dialog.dismiss();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setCancelable(true).create();

        /* Bottom Navigation View */
        bottomNavMain.setOnNavigationItemSelectedListener(this);
        bottomNavMain.setSelectedItemId(R.id.nav_popular);

        FIRST_LOADING = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
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
                if (!isUserLogin && !FIRST_LOADING) {
                    mLoginAlert.show();
                    return false;
                } else if (isUserLogin){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, MyCafeFragment.newInstance(), "MYCAFE").commit();
                    return true;
                }
            case R.id.nav_popular:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, PopularFragment.newInstance(), "POPULAR").commit();
                return true;
            case R.id.nav_notification:
                if (!isUserLogin && !FIRST_LOADING) {
                    mLoginAlert.show();
                    return false;
                } else if (isUserLogin){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, NotificationFragment.newInstance(), "NOTIFICATION").commit();
                    return true;
                }
            case R.id.nav_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, SettingFragment.newInstance(), "SETTING").commit();
                return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
