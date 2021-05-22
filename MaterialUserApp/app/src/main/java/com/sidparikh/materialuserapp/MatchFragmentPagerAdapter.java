package com.sidparikh.materialuserapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MatchFragmentPagerAdapter extends FragmentStateAdapter {

    /* Fragments:
       0 AutoFragment
       1 TeleopFragment
       2 MapFragment
       3 EndgameFragment
     */

    public static final int[] TAB_NAMES = new int[]{R.string.tab_auto, R.string.tab_teleop};
    private static final int TAB_COUNT = TAB_NAMES.length;

    private static Class[] test = new Class[]{AutoFragment.class};

    public MatchFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AutoFragment();
            case 1:
                return new TeleopFragment();
        }
        // This should never happen and will obviously create a NullPointerException.
        return null;
    }

    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
