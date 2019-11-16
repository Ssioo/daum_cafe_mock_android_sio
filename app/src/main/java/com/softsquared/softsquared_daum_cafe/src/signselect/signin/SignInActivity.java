package com.softsquared.softsquared_daum_cafe.src.signselect.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.MainActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.interfaces.SignInActivityView;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_ID;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_NAME;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class SignInActivity extends BaseActivity implements SignInActivityView {

    private Button btnSignIn;
    private EditText etEmail;
    private EditText etPw;
    private TextView tvFindEmail;
    private TextView tvFindPw;
    private CheckBox chkAutoLogin;
    private ImageView ivClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        /* findViewByID */
        btnSignIn = findViewById(R.id.btn_signin_signin);
        etEmail = findViewById(R.id.et_email_signin);
        etPw = findViewById(R.id.et_pw_signin);
        tvFindEmail = findViewById(R.id.tv_findemail_signin);
        tvFindPw = findViewById(R.id.tv_findpw_signin);
        chkAutoLogin = findViewById(R.id.chk_autologin_signin);
        ivClose = findViewById(R.id.iv_close_signin);

        /* Set On Click Listener */
        btnSignIn.setOnClickListener(this);
        tvFindPw.setOnClickListener(this);
        tvFindEmail.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signin_signin:
                trySignIn();
                break;
            case R.id.iv_close_signin:
                finish();
                break;
            case R.id.tv_findemail_signin:
            case R.id.tv_findpw_signin:
                showToast(getString(R.string.nofunction));
                break;
        }
    }

    private void trySignIn() {
        showProgressDialog();

        final SignInService signInService = new SignInService(this);
        signInService.getSignIn(etEmail.getText().toString(), etPw.getText().toString());
    }

    @Override
    public void validateSuccess(String token, String id, String name) {
        hideProgressDialog();
        showToast("로그인에 성공하였습니다.");
        sSharedPreferences.edit().putBoolean(USER_LOGINNED, true);
        sSharedPreferences.edit().putString(USER_NAME, id).apply();
        sSharedPreferences.edit().putString(USER_ID, id).apply();
        sSharedPreferences.edit().putString(X_ACCESS_TOKEN, token).apply();
        startNextActivity(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
