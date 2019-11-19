package com.softsquared.softsquared_daum_cafe.src.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.search.fragments.search_ad.SearchAdFragment;
import com.softsquared.softsquared_daum_cafe.src.search.fragments.search_history.SearchHistoryFragment;
import com.softsquared.softsquared_daum_cafe.src.search.interfaces.SearchActivityView;

public class SearchActivity extends BaseActivity implements SearchActivityView {

    private Toolbar tbSearch;
    private EditText etSearch;
    private ConstraintLayout clSearch;


    private boolean MODE_ADVERTISE = true;
    private boolean BACK_STACKED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        /* findViewByID */
        tbSearch = findViewById(R.id.toolbar_search);
        clSearch = findViewById(R.id.cl_search);
        etSearch = findViewById(R.id.et_searchview_search);

        /* get Intent */
        Intent intent = getIntent();
        MODE_ADVERTISE = intent.getBooleanExtra("MODE_ADVERTISE", true);

        /* Toolbar */
        setSupportActionBar(tbSearch);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black);

        /* Set On Click Listener */
        etSearch.setOnTouchListener(this);

        /* Init View */
        if (MODE_ADVERTISE)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.cl_search, SearchAdFragment.newInstance()).commit();
        else
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.cl_search, SearchHistoryFragment.newInstance(), "HISTORY").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.tb_search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (MODE_ADVERTISE && BACK_STACKED) {
            BACK_STACKED = false;
        }
        super.onBackPressed();
    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.et_searchview_search:
                if (MODE_ADVERTISE && !BACK_STACKED) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.cl_search, SearchHistoryFragment.newInstance(), "HISTORY").addToBackStack("HISTORY").commit();
                    BACK_STACKED = true;
                }
                break;
        }
        return false;
    }
}
