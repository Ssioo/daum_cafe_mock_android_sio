package com.softsqaured.softsquared_daum_cafe.src.main.fragments.notification.fragments.newarticlenotification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;

public class NewArticleNotificationFragment extends BaseFragment {

    public NewArticleNotificationFragment() {
    }

    public static NewArticleNotificationFragment newInstance() {
        NewArticleNotificationFragment fragment = new NewArticleNotificationFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newarticle_notification , container, false);
        return view;
    }
}
