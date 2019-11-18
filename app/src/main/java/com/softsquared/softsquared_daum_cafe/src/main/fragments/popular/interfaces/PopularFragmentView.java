package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.interfaces;

import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.PopularResponse;

import java.util.ArrayList;

public interface PopularFragmentView extends View.OnClickListener
        , AppBarLayout.OnOffsetChangedListener, ViewPager.OnPageChangeListener, DrawerLayout.DrawerListener {

    void validateSuccess(ArrayList<PopularResponse.Result> results);

    void validateFailure(String message);
}
