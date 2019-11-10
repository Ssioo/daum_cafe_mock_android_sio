package com.softsquared.softsquared_daum_cafe.src.signselect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.SignInActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.interfaces.SignSelectActivityView;
import com.softsquared.softsquared_daum_cafe.src.signselect.signup.SignUpActivity;

public class SignSelectActivity extends BaseActivity implements SignSelectActivityView {

    private Button btnSignIn;
    private Button btnKakaoSignIn;
    private TextView tvSignUp;
    private TextView tvKakaoDirectSignIn;
    private ImageView ivClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_select);

        /* findViewByID */
        btnSignIn = findViewById(R.id.btn_signin_signselect);
        btnKakaoSignIn = findViewById(R.id.btn_kakao_signin_signselect);
        tvSignUp = findViewById(R.id.tv_signup_signselect);
        tvKakaoDirectSignIn = findViewById(R.id.tv_kakao_direct_signin_signselect);
        ivClose = findViewById(R.id.iv_close_signselect);

        /* Set On Click Listener */
        btnSignIn.setOnClickListener(this);
        btnKakaoSignIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        tvKakaoDirectSignIn.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signin_signselect:
                startNextActivity(SignInActivity.class);
                break;
            case R.id.btn_kakao_signin_signselect:
                showToast("미구현 기능입니다");
                break;
            case R.id.tv_signup_signselect:
                startNextActivity(SignUpActivity.class);
                break;
            case R.id.tv_kakao_direct_signin_signselect:
                showToast("미구현 기능입니다");
                break;
            case R.id.iv_close_signselect:
                finish();
                break;
        }
    }
}
