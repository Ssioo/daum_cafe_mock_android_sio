package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.friendlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;

public class FriendListFragment extends BaseFragment {

    public FriendListFragment() {
    }

    public static FriendListFragment newInstance() {

        FriendListFragment fragment = new FriendListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycafe_friend, container, false);
        return view;
    }
}
