package com.softsquared.softsquared_daum_cafe.src.signselect.signup;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signin.SignInActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signup.interfaces.SignUpActivityView;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    private ImageView ivClose;
    private Button btnSignUp;
    private EditText etEmail;
    private TextView tvEmailErr;
    private EditText etPassword;
    private TextView tvPwErr;
    private EditText etPasswordCheck;
    private TextView tvPwCheckErr;
    private EditText etName;
    private TextView tvNameErr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /* findViewByID */
        ivClose = findViewById(R.id.iv_close_signup);
        btnSignUp = findViewById(R.id.btn_signup_signup);
        etEmail = findViewById(R.id.et_email_signup);
        tvEmailErr = findViewById(R.id.tv_email_err_signup);
        etPassword = findViewById(R.id.et_pw_signup);
        tvPwErr = findViewById(R.id.tv_pw_err_signup);
        etPasswordCheck = findViewById(R.id.et_pwcheck_signup);
        tvPwCheckErr = findViewById(R.id.tv_pwcheck_err_signup);
        etName = findViewById(R.id.et_name_signup);
        tvNameErr = findViewById(R.id.tv_name_err_signup);

        /* Set on Click Listener */
        ivClose.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        /* Text Watcher */
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().lastIndexOf('@') == -1) {
                    return;
                }
                if (!s.toString().substring(s.toString().lastIndexOf('@')).equals("@daum.net") || s.toString().length() <= 0) {
                    tvEmailErr.setTextColor(getColor(android.R.color.holo_red_light));
                } else {
                    tvEmailErr.setTextColor(getColor(android.R.color.holo_green_light));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() < 6 || s.toString().length() > 20) {
                    tvPwErr.setTextColor(getColor(android.R.color.holo_red_light));
                } else {
                    tvPwErr.setTextColor(getColor(android.R.color.holo_green_light));
                }
                if (!tvPwCheckErr.getText().toString().equals("")) {
                    tvPwCheckErr.setText(getString(R.string.type_pwcheck_desc_no_signup));
                    tvPwCheckErr.setTextColor(getColor(android.R.color.holo_red_light));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPasswordCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(etPassword.getText().toString())) {
                    tvPwCheckErr.setText(getString(R.string.type_pwcheck_desc_ok_signup));
                    tvPwCheckErr.setTextColor(getColor(android.R.color.holo_green_light));
                } else {
                    tvPwCheckErr.setText(getString(R.string.type_pwcheck_desc_no_signup));
                    tvPwCheckErr.setVisibility(View.VISIBLE);
                    tvPwCheckErr.setTextColor(getColor(android.R.color.holo_red_light));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 20 || s.toString().length() == 0) {
                    tvNameErr.setTextColor(getColor(android.R.color.holo_red_light));
                } else {
                    tvNameErr.setTextColor(getColor(android.R.color.holo_green_light));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_signup:
                finish();
                break;
            case R.id.btn_signup_signup:
                trySignUp(etEmail.getText().toString(), etPassword.getText().toString(), etPasswordCheck.getText().toString(), etName.getText().toString());
                break;
        }
    }

    private void trySignUp(String email, String password, String passwordCheck, String name) {
        boolean emailError = !email.substring(email.lastIndexOf('@')).equals("@daum.net") || email.length() <= 0;
        boolean pwLenError = password.length() < 6 || password.length() > 20;
        boolean pwCheckError = !passwordCheck.equals(password) || passwordCheck.length() <= 0;
        boolean nameLenError = name.length() > 20 || name.length() <= 0;
        if (emailError || pwLenError || pwCheckError || nameLenError) {
            new AlertDialog.Builder(this).setMessage("입력한 내용을 다시 확인해주세요.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
            return;
        }
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
