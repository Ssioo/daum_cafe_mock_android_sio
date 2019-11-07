package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.boardlist.BoardListFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.bookmarklist.BookmarkListFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.CafeListFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.friendlist.FriendListFragment;

public class MyCafePagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;

    public MyCafePagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CafeListFragment.newInstance();
            case 1:
                return BoardListFragment.newInstance();
            case 2:
                return FriendListFragment.newInstance();
            case 3:
                return BookmarkListFragment.newInstance();
        }
        return null;
    }


    @Override
    public int getCount() {
        return mPageCount;
    }
}
