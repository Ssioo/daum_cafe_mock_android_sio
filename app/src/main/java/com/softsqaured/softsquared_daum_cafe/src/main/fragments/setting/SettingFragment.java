package com.softsqaured.softsquared_daum_cafe.src.main.fragments.setting;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.signselect.SignSelectActivity;

public class SettingFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener{

    Preference prefLogin;

    public SettingFragment() {
    }

    public static SettingFragment newInstance() {

        SettingFragment fragment = new SettingFragment();
        return fragment;
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_setting);

        /* findPreference */
        prefLogin = findPreference("signin");

        prefLogin.setOnPreferenceClickListener(this);
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "signin":
                Intent intent = new Intent(getActivity(), SignSelectActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}
