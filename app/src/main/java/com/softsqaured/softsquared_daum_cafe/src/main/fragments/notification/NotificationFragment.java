package com.softsqaured.softsquared_daum_cafe.src.main.fragments.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.MyCafePagerAdapter;

public class NotificationFragment extends BaseFragment implements TabLayout.OnTabSelectedListener{

    private TabLayout tlNotification;
    private ViewPager vpNotification;

    private NotificationPagerAdapter npAdapter;

    public NotificationFragment() {
    }

    public static NotificationFragment newInstance() {

        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        /* findViewByID */
        tlNotification = view.findViewById(R.id.tab_notification);
        vpNotification = view.findViewById(R.id.vp_notification);

        /* ViewPager */
        npAdapter = new NotificationPagerAdapter(getChildFragmentManager(), 3);
        vpNotification.setAdapter(npAdapter);

        /* ViewPager Add on Listener */
        vpNotification.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlNotification));

        /* TabLayout Add on Tab Selected Listener */
        tlNotification.addOnTabSelectedListener(this);

        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vpNotification.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
