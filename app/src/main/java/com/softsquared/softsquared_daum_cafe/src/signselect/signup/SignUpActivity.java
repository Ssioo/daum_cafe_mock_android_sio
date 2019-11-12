package com.softsquared.softsquared_daum_cafe.src.signselect.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.SignInActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signup.interfaces.SignUpActivityView;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    private ImageView ivClose;
    private Button btnSignUp;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /* findViewByID */
        ivClose = findViewById(R.id.iv_close_signup);
        btnSignUp = findViewById(R.id.btn_signup_signup);
        etEmail = findViewById(R.id.et_email_signup);
        etPassword = findViewById(R.id.et_pw_signup);
        etName = findViewById(R.id.et_name_signup);

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
                trySignUp(etEmail.getText().toString(), etPassword.getText().toString(), etName.getText().toString());
                break;
        }
    }

    private void trySignUp(String email, String password, String name) {
        showProgressDialog();
        final SignUpService signUpService = new SignUpService(this);
        signUpService.getSignIn(email, password, name);
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        showToast("회원가입에 성공하였습니다. 다시 로그인해주세요.");
        startNextActivity(SignInActivity.class);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
