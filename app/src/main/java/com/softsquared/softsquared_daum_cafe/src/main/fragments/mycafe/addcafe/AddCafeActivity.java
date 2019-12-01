package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.interfaces.AddCafeActivityView;

public class AddCafeActivity extends BaseActivity implements AddCafeActivityView {

    private Toolbar tbAddCafe;
    private EditText etCafeName;
    private EditText etCafeUrl;
    private TextView tvCafeNameCount;
    private TextView tvCafeUrlCount;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcafe);

        /* findViweByID */
        tbAddCafe = findViewById(R.id.toolbar_addcafe);
        etCafeName = findViewById(R.id.et_cafe_name_addcafe);
        etCafeUrl = findViewById(R.id.et_cafe_url_addcafe);
        btnSubmit = findViewById(R.id.btn_addcafe_addcafe);
        tvCafeNameCount = findViewById(R.id.tv_cafe_name_count_addcafe);
        tvCafeUrlCount = findViewById(R.id.tv_cafe_url_count_addcafe);


        /* Toolbar */
        setSupportActionBar(tbAddCafe);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_black);

        /* Set On Click Listener */
        btnSubmit.setOnClickListener(this);

        /* Add TextWatcher */
        etCafeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 20) {
                    return;
                }
                tvCafeNameCount.setText(s.length() + "/60");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCafeUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCafeUrlCount.setText(s.length() + "/20");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addcafe_addcafe:
                if (!etCafeName.getText().toString().equals(""))
                    postCafe(etCafeName.getText().toString());
                break;
        }
    }

    private void postCafe(String cafeName) {
        final AddCafeService addCafeService = new AddCafeService(this);
        addCafeService.postCafe(cafeName);
    }

    @Override
    public void validateSuccess(String text) {
        showToast(getString(R.string.success_addcafe));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        showToast((message == null) ? getString(R.string.network_error) : getString(R.string.failure_addcafe));
    }
}
