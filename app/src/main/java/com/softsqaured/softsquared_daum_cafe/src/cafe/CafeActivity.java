package com.softsqaured.softsquared_daum_cafe.src.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseActivity;
import com.softsqaured.softsquared_daum_cafe.src.cafe.interfaces.CafeActivityView;

public class CafeActivity extends BaseActivity implements CafeActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
