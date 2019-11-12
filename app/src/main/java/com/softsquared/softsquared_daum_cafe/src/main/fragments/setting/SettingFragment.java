package com.softsquared.softsquared_daum_cafe.src.main.fragments.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseFragment;
import com.softsquared.softsquared_daum_cafe.src.main.MainActivity;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.setting.interfaces.SettingFragmentView;
import com.softsquared.softsquared_daum_cafe.src.signselect.SignSelectActivity;
import com.softsquared.softsquared_daum_cafe.src.signselect.signout.SignOutActivity;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.isUserLogin;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.userName;

public class SettingFragment extends BaseFragment implements SettingFragmentView {

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    private Toolbar tbSetting;
    private LinearLayout llItemSignInfo;
    private LinearLayout llItemVersion;
    private LinearLayout llItemNotiSet;
    private LinearLayout llItemNotiCheck;
    private LinearLayout llItemWrite;
    private LinearLayout llItemRead;
    private LinearLayout llItemBoard;
    private LinearLayout llItemSpamMail;
    private LinearLayout llItemHomePage;
    private LinearLayout llItemHomeMyCafe;
    private LinearLayout llItemTheme;
    private LinearLayout llItemLanguage;
    private LinearLayout llItemLock;
    private LinearLayout llItemCache;
    private LinearLayout llItemLab;
    private LinearLayout llItemServiceInfo;
    private TextView tvUserNameSetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        /* findViewByID */
        tbSetting = view.findViewById(R.id.toolbar_setting);
        llItemSignInfo = view.findViewById(R.id.item_signinfo_setting);
        llItemVersion = view.findViewById(R.id.item_version_setting);
        llItemNotiSet = view.findViewById(R.id.item_noti_set_setting);
        llItemNotiCheck = view.findViewById(R.id.item_noti_check_setting);
        llItemWrite = view.findViewById(R.id.item_write_setting);
        llItemRead = view.findViewById(R.id.item_read_setting);
        llItemBoard = view.findViewById(R.id.item_board_setting);
        llItemSpamMail = view.findViewById(R.id.item_spammail_setting);
        llItemHomePage = view.findViewById(R.id.item_homepage_setting);
        llItemHomeMyCafe = view.findViewById(R.id.item_homemycafe_setting);
        llItemTheme = view.findViewById(R.id.item_theme_setting);
        llItemLanguage = view.findViewById(R.id.item_language_setting);
        llItemLock = view.findViewById(R.id.item_lock_setting);
        llItemCache = view.findViewById(R.id.item_cache_setting);
        llItemLab = view.findViewById(R.id.item_lab_setting);
        llItemServiceInfo = view.findViewById(R.id.item_serviceinfo_setting);
        tvUserNameSetting = view.findViewById(R.id.tv_setting_signinfo_desc);

        /* Toolbar */
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setSupportActionBar(tbSetting);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        /* Setting View Init*/
        if (!isUserLogin) {
            tvUserNameSetting.setText("로그인 하세요");
        } else {
            tvUserNameSetting.setText(userName);
        }

        /* Set On Click Listener */
        llItemSignInfo.setOnClickListener(this);
        llItemVersion.setOnClickListener(this);
        llItemNotiSet.setOnClickListener(this);
        llItemNotiCheck.setOnClickListener(this);
        llItemWrite.setOnClickListener(this);
        llItemRead.setOnClickListener(this);
        llItemBoard.setOnClickListener(this);
        llItemSpamMail.setOnClickListener(this);
        llItemHomePage.setOnClickListener(this);
        llItemHomeMyCafe.setOnClickListener(this);
        llItemTheme.setOnClickListener(this);
        llItemLanguage.setOnClickListener(this);
        llItemLock.setOnClickListener(this);
        llItemCache.setOnClickListener(this);
        llItemLab.setOnClickListener(this);
        llItemServiceInfo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_signinfo_setting:
                if (!isUserLogin)
                    startNextActivity(SignSelectActivity.class);
                else
                    startNextActivity(SignOutActivity.class);
                break;
            case R.id.item_version_setting:
                showToast("최신 버전입니다.");
                break;
            case R.id.item_noti_set_setting:
                break;
            case R.id.item_noti_check_setting:
                break;
            case R.id.item_write_setting:
                break;
            case R.id.item_read_setting:
                break;
            case R.id.item_board_setting:
                break;
            case R.id.item_spammail_setting:
                break;
            case R.id.item_homepage_setting:
                break;
            case R.id.item_homemycafe_setting:
                break;
            case R.id.item_theme_setting:
                break;
            case R.id.item_language_setting:
                break;
            case R.id.item_lock_setting:
                break;
            case R.id.item_cache_setting:
                break;
            case R.id.item_lab_setting:
                break;
            case R.id.item_serviceinfo_setting:
                break;
        }
    }
}
