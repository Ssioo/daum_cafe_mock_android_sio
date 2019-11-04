package com.softsqaured.softsquared_daum_cafe.src.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseActivity;
import com.softsqaured.softsquared_daum_cafe.src.signup.interfaces.SignUpActivityView;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
