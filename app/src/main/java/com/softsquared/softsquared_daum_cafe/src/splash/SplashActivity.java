package com.softsquared.softsquared_daum_cafe.src.splash;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNextActivity(MainActivity.class);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        }, 300);


    }
}
