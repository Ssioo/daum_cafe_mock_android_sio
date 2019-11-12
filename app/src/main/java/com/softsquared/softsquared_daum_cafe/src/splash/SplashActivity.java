package com.softsquared.softsquared_daum_cafe.src.splash;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.MainActivity;
import com.softsquared.softsquared_daum_cafe.src.splash.interfaces.SplashActivityView;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.isUserLogin;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.userId;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.userName;

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
        isUserLogin = true;
        userId = email;
        userName = name;
        Log.i("AUTOLOGIN", "SUCCESS");
        startNextActivity(MainActivity.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        isUserLogin = false;
        Log.i("AUTOLOGIN", "FAILED");
        startNextActivity(MainActivity.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
}
