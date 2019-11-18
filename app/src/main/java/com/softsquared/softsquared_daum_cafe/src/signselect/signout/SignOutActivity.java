package com.softsquared.softsquared_daum_cafe.src.signselect.signout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signout.interfaces.SignOutActivityView;
import com.softsquared.softsquared_daum_cafe.src.splash.SplashActivity;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_ID;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class SignOutActivity extends BaseActivity implements SignOutActivityView {

    private ImageView ivClose;
    private TextView tvUserId;
    private TextView tvIntegrate;
    private Button btnSignOut;
    private TextView tvAddAnotherId;

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

        /* Set On Click Listener */
        ivClose.setOnClickListener(this);
        tvIntegrate.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        tvAddAnotherId.setOnClickListener(this);

        /* Set View */
        tvUserId.setText(sSharedPreferences.getString(USER_ID, null));
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
                new AlertDialog.Builder(this).setMessage("로그아웃하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sSharedPreferences.edit().remove(X_ACCESS_TOKEN).apply();
                                sSharedPreferences.edit().putBoolean(USER_LOGINNED, false).apply();
                                dialog.dismiss();
                                startNextActivity(SplashActivity.class);
                                finish();
                            }
                        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();

                break;
            case R.id.tv_add_anotherid_signout:
                showToast(getString(R.string.nofunction));
                break;
        }
    }
}
