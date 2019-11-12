package com.softsquared.softsquared_daum_cafe.src.signselect.signout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signout.interfaces.SignOutActivityView;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.isUserLogin;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.userId;

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
        tvUserId.setText(userId);
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
                sSharedPreferences.edit().putString(X_ACCESS_TOKEN, null).apply();
                isUserLogin = false;
                finish();
                break;
            case R.id.tv_add_anotherid_signout:
                showToast(getString(R.string.nofunction));
                break;
        }
    }
}
