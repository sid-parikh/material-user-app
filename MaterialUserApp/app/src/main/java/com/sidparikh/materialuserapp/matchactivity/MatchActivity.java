package com.sidparikh.materialuserapp.matchactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sidparikh.materialuserapp.MainActivity;
import com.sidparikh.materialuserapp.R;
import com.sidparikh.materialuserapp.data.Whoosh;

/**
 * This activity is the main form-filling activity. It contains a ViewPager which switches through
 * the three fragments. It handles the counter-buttons and checkboxes on those fragments directly by
 * implementing Listeners.
 */

public class MatchActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener, CounterView.ScoreChangeListener {

    private static final String WHOOSH_KEY = "whoosh";

    private Whoosh data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        // Set the toolbar to be the ActionBar. This allows this activity to handle its events.
        MaterialToolbar toolbar = findViewById(R.id.toolbar_match);
        setSupportActionBar(toolbar);

        // Setup the viewpager and TabLayout to work together, using the subclassed Adapter to
        // handle changes.
        ViewPager2 viewPager = findViewById(R.id.pager_match);
        TabLayout tabLayout = findViewById(R.id.tabLayout_match);
        FragmentStateAdapter pagerAdapter = new MatchFragmentPagerAdapter(this);

        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(MatchFragmentPagerAdapter.TAB_NAMES[position]);
                    }
                })).attach();

        // This field will store all collected data as the user enters it.
        data = new Whoosh();

        // Get the data passed from MainActivity and add it to the Whoosh.
        // Note that the defaults should never be needed as MainActivity should guarantee these
        // three extras.
        Intent intent = getIntent();
        data.setAlliance(intent.getBooleanExtra(MainActivity.ALLIANCE_EXTRA, true));
        data.setTeam(intent.getIntExtra(MainActivity.TEAM_EXTRA, 0));
        data.setMatch(intent.getIntExtra(MainActivity.MATCH_EXTRA, 0));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Save the current data during config changes
        outState.putSerializable(WHOOSH_KEY, data);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.data = (Whoosh) savedInstanceState.getSerializable(WHOOSH_KEY);
    }

    /**
     * Handles clicks for any CompoundButton (CheckBox, ToggleButton, RadioButton) within the
     * fragments. Saves the data into the Whoosh.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // Teleop checkboxes (Wheel of Fortune)
        int id = buttonView.getId();
        if (id == R.id.check_rotation) {
            data.setRotationControl(isChecked);
        } else if (id == R.id.check_position) {
            data.setPositionControl(isChecked);
        }
    }

    /**
     * Handles the changes in any of the scores of any CounterView in the fragments.
     * Saves the data into the Whoosh.
     */
    @Override
    public void onScoreChanged(CounterView counterView, int newScore) {
        int id = counterView.getId();
        if (id == R.id.counter_auto_inner) {
            data.setAutoInnerCells(newScore);
        } else if (id == R.id.counter_auto_outer) {
            data.setAutoOuterCells(newScore);
        } else if (id == R.id.counter_auto_lower) {
            data.setAutoLowerCells(newScore);
        } else if (id == R.id.counter_auto_pickup) {
            data.setAutoPickupCells(newScore);
        } else if (id == R.id.counter_teleop_inner) {
            data.setTeleopInnerCells(newScore);
        } else if (id == R.id.counter_teleop_outer) {
            data.setTeleopOuterCells(newScore);
        } else if (id == R.id.counter_teleop_lower) {
            data.setTeleopLowerCells(newScore);
        }
    }
}
