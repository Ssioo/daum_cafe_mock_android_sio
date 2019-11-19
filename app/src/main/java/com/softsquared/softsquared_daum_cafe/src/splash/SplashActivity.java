package com.softsquared.softsquared_daum_cafe.src.splash;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.MainActivity;
import com.softsquared.softsquared_daum_cafe.src.splash.interfaces.SplashActivityView;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.FCM_TOKEN;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.FCM_TOKEN_POSTED;
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
        if (sSharedPreferences.getBoolean(FCM_TOKEN_POSTED, false)) {
            // mSplashService.getFCMToken();
        } else {
            mSplashService.getAutoLogin();
        }


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

    @Override
    public void validateFCMTokenGetSuccess(String token) {
        sSharedPreferences.edit().putString(FCM_TOKEN, token).apply();
        final SplashService splashService = new SplashService(this);
        splashService.postFCMToken(token);
    }

    @Override
    public void validateFCMTokenGetFailure(String message) {
        showToast((message == null) ? "FCM TOKEN 등록 실패" : "실패");
    }

    @Override
    public void validateFCMTokenPostSuccess(String message) {
        sSharedPreferences.edit().putBoolean(FCM_TOKEN_POSTED, true).apply();
        final SplashService splashService = new SplashService(this);
        splashService.getAutoLogin();
    }

    @Override
    public void validateFCMTokenPostFailure(String message) {
        final SplashService splashService = new SplashService(this);
        splashService.getAutoLogin();
    }
}
