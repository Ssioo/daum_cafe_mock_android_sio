package com.softsquared.softsquared_daum_cafe.src.splash;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.MainActivity;
import com.softsquared.softsquared_daum_cafe.src.splash.interfaces.SplashActivityView;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_EMAIL;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_NAME;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class SplashActivity extends BaseActivity implements SplashActivityView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SplashService mSplashService = new SplashService(this);
        mSplashService.getAutoLogin();
    }


    @Override
    public void validateSuccess(String email, String name) {
        sSharedPreferences.edit().putBoolean(USER_LOGINNED, true).apply();
        sSharedPreferences.edit().putString(USER_EMAIL, email).apply();
        sSharedPreferences.edit().putString(USER_NAME, name).apply();
        Log.i("AUTOLOGIN", "SUCCESS");
        startNextActivity(MainActivity.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        sSharedPreferences.edit().putBoolean(USER_LOGINNED, false).apply();
        Log.i("AUTOLOGIN", "FAILED");
        startNextActivity(MainActivity.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
}
