package com.softsqaured.softsquared_daum_cafe.src.main.fragments.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.notification.interfaces.NotificationFragmentView;

public class NotificationFragment extends BaseFragment implements NotificationFragmentView, TabLayout.OnTabSelectedListener {

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
        tlNotification.getTabAt(0).setCustomView(createTabView(inflater, "0", "카페활동알림"));
        tlNotification.getTabAt(1).setCustomView(createTabView(inflater, "0", "새글알림"));
        tlNotification.getTabAt(2).setCustomView(createTabView(inflater, "0", "쪽지"));

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

    public View createTabView(LayoutInflater inflater, String title, String subTitle) {
        View tabView = inflater.inflate(R.layout.item_tab_notification, null);
        TextView tvTabTitle = tabView.findViewById(R.id.tab_title);
        TextView tvTabSubTitle = tabView.findViewById(R.id.tab_subtitle);
        tvTabTitle.setText(title);
        tvTabSubTitle.setText(subTitle);

        return tabView;
    }
}
