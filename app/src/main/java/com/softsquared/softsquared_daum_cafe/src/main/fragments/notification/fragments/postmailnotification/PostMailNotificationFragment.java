package com.softsquared.softsquared_daum_cafe.src.main.fragments.notification.fragments.postmailnotification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;

public class PostMailNotificationFragment extends BaseFragment {

    public PostMailNotificationFragment() {
    }

    public static PostMailNotificationFragment newInstance() {

        PostMailNotificationFragment fragment = new PostMailNotificationFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_postmail, container, false);
        return view;
    }
}
