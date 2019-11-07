package com.softsqaured.softsquared_daum_cafe.src.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseActivity;
import com.softsqaured.softsquared_daum_cafe.src.signup.interfaces.SignUpActivityView;

public class SignUpActivity extends BaseActivity implements SignUpActivityView, View.OnClickListener {

    private ImageView ivClose;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /* findViewByID */
        ivClose = findViewById(R.id.iv_close_signup);
        btnSignUp = findViewById(R.id.btn_signup_signup);

        /* Set on Click Listener */
        ivClose.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_signup:
                finish();
                break;
            case R.id.btn_signup_signup:
                break;
        }
    }
}
