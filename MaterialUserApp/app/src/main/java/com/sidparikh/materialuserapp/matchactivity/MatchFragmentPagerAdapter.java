package com.sidparikh.materialuserapp.matchactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sidparikh.materialuserapp.R;
import com.sidparikh.materialuserapp.matchactivity.fragments.AutoFragment;
import com.sidparikh.materialuserapp.matchactivity.fragments.MapFragment;
import com.sidparikh.materialuserapp.matchactivity.fragments.TeleopFragment;

public class MatchFragmentPagerAdapter extends FragmentStateAdapter {

    /* Fragments:
       0 AutoFragment
       1 TeleopFragment
       2 MapFragment
       3 EndgameFragment
     */

    public static final int[] TAB_NAMES = new int[]{R.string.tab_auto, R.string.tab_teleop, R.string.tab_map};
    private static final int TAB_COUNT = TAB_NAMES.length;

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
            case 2:
                return new MapFragment();
        }
        // This should never happen and will obviously create a NullPointerException.
        return null;
    }

    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
