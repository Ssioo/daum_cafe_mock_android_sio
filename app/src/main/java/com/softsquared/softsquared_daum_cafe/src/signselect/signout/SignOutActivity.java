package com.softsquared.softsquared_daum_cafe.src.signselect.signout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.MainActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signout.interfaces.SignOutActivityView;
import com.softsquared.softsquared_daum_cafe.src.splash.SplashActivity;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_EMAIL;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class SignOutActivity extends BaseActivity implements SignOutActivityView {

    private ImageView ivClose;
    private TextView tvUserId;
    private TextView tvIntegrate;
    private Button btnSignOut;
    private TextView tvAddAnotherId;
    private TextView tvResign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);

        /* findViewById */
        ivClose = findViewById(R.id.iv_close_signout);
        tvUserId = findViewById(R.id.tv_userid_signout);
        tvIntegrate = findViewById(R.id.tv_integrate_signout);
        btnSignOut = findViewById(R.id.btn_signout_signout);
        tvAddAnotherId = findViewById(R.id.tv_add_anotherid_signout);
        tvResign = findViewById(R.id.tv_resign_signout);

        /* Set On Click Listener */
        ivClose.setOnClickListener(this);
        tvIntegrate.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        tvAddAnotherId.setOnClickListener(this);
        tvResign.setOnClickListener(this);

        /* Set View */
        tvUserId.setText(sSharedPreferences.getString(USER_EMAIL, null));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_signout:
                finish();
                break;
            case R.id.tv_integrate_signout:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.btn_signout_signout:
                // 로그아웃
                new AlertDialog.Builder(this).setMessage(getString(R.string.alert_logout))
                        .setPositiveButton(getString(R.string.alert_positive), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sSharedPreferences.edit().remove(X_ACCESS_TOKEN).apply();
                                sSharedPreferences.edit().putBoolean(USER_LOGINNED, false).apply();
                                dialog.dismiss();
                                startNextActivity(SplashActivity.class);
                                finish();
                            }
                        }).setNegativeButton(getString(R.string.alert_negative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();

                break;
            case R.id.tv_add_anotherid_signout:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.tv_resign_signout:
                // 회원탈퇴
                new AlertDialog.Builder(this).setMessage(getString(R.string.alert_resign))
                        .setPositiveButton(getString(R.string.alert_positive), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sSharedPreferences.edit().remove(X_ACCESS_TOKEN).apply();
                                sSharedPreferences.edit().putBoolean(USER_LOGINNED, false).apply();
                                tryResign();
                                dialog.dismiss();
                            }
                        }).setNegativeButton(getString(R.string.alert_negative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
        }
    }

    private void tryResign() {
        showProgressDialog();
        final SignOutService signOutService = new SignOutService(this);
        //signOutService.tryResign();
    }


    @Override
    public void validateSuccess(String message) {
        hideProgressDialog();
        showToast(getString(R.string.success_resign));
        startNextActivity(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast((message == null) ? getString(R.string.network_error) : message);
    }
}
