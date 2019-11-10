package com.softsquared.softsquared_daum_cafe.src.main.fragments.notification;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.notification.fragments.cafeactnotifications.CafeActNotificationFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.notification.fragments.newarticlenotification.NewArticleNotificationFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.notification.fragments.postmailnotification.PostMailNotificationFragment;

public class NotificationPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;

    public NotificationPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CafeActNotificationFragment.newInstance();
            case 1:
                return NewArticleNotificationFragment.newInstance();
            case 2:
                return PostMailNotificationFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
