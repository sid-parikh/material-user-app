package com.sidparikh.materialuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * This activity is the main form-filling activity. It contains a ViewPager which switches through
 * the three fragments. It handles the counter-buttons and checkboxes on those fragments directly by
 * implementing Listeners.
 */

public class MatchActivity extends AppCompatActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final String WHOOSH_KEY = "whoosh";

    private Whoosh data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        // Set the toolbar to be the ActionBar. This allows this activity to handle its events.
        MaterialToolbar toolbar = findViewById(R.id.toolbar_match);
        setSupportActionBar(toolbar);

        // Setup the viewpager and tablayout to work together, using the subclassed Adapter to
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
     * Handles clicks for any button within the fragments.
     *
     * @param v clicked button
     */

    @Override
    public void onClick(View v) {
        TextView scoreView;

        switch (v.getId()) {

            /*
               For increment and decrement buttons, use the incrementScore and decrementScore
               methods, which update TextViews and return the new value. Immediately save the new
               value to the Whoosh.
             */

            // Auto Buttons
            case R.id.button_auto_inner_plus:
                scoreView = findViewById(R.id.text_auto_inner_score);
                data.setAutoInnerCells(incrementScore(scoreView));
                break;
            case R.id.button_auto_inner_minus:
                scoreView = findViewById(R.id.text_auto_inner_score);
                data.setAutoInnerCells(decrementScore(scoreView));
                break;
            case R.id.button_auto_outer_plus:
                scoreView = findViewById(R.id.text_auto_outer_score);
                data.setAutoOuterCells(incrementScore(scoreView));
                break;
            case R.id.button_auto_outer_minus:
                scoreView = findViewById(R.id.text_auto_outer_score);
                data.setAutoOuterCells(decrementScore(scoreView));
                break;
            case R.id.button_auto_lower_plus:
                scoreView = findViewById(R.id.text_auto_lower_score);
                data.setAutoLowerCells(incrementScore(scoreView));
                break;
            case R.id.button_auto_lower_minus:
                scoreView = findViewById(R.id.text_auto_lower_score);
                data.setAutoLowerCells(decrementScore(scoreView));
                break;
            case R.id.button_auto_pickup_plus:
                scoreView = findViewById(R.id.text_auto_pickup_score);
                data.setAutoPickupCells(incrementScore(scoreView));
                break;
            case R.id.button_auto_pickup_minus:
                scoreView = findViewById(R.id.text_auto_pickup_score);
                data.setAutoPickupCells(decrementScore(scoreView));
                break;

            // Teleop Buttons
            case R.id.button_teleop_inner_plus:
                scoreView = findViewById(R.id.text_teleop_inner_score);
                data.setTeleopInnerCells(incrementScore(scoreView));
                break;
            case R.id.button_teleop_inner_minus:
                scoreView = findViewById(R.id.text_teleop_inner_score);
                data.setTeleopInnerCells(decrementScore(scoreView));
                break;
            case R.id.button_teleop_outer_plus:
                scoreView = findViewById(R.id.text_teleop_outer_score);
                data.setTeleopOuterCells(incrementScore(scoreView));
                break;
            case R.id.button_teleop_outer_minus:
                scoreView = findViewById(R.id.text_teleop_outer_score);
                data.setTeleopOuterCells(decrementScore(scoreView));
                break;
            case R.id.button_teleop_lower_plus:
                scoreView = findViewById(R.id.text_teleop_lower_score);
                data.setTeleopLowerCells(incrementScore(scoreView));
                break;
            case R.id.button_teleop_lower_minus:
                scoreView = findViewById(R.id.text_teleop_lower_score);
                data.setTeleopLowerCells(decrementScore(scoreView));
                break;
        }
    }

    /**
     * Handles clicks for any compoundbutton (checkbox, togglebutton, radiobutton) within the
     * fragments. Saves the data into the Whoosh.
     */

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // Teleop checkboxes (Wheel of Fortune)
        switch (buttonView.getId()) {
            case R.id.check_rotation:
                data.setRotationControl(isChecked);
                break;
            case R.id.check_position:
                data.setPositionControl(isChecked);
                break;
        }
    }

    /**
     * Takes any {@link TextView}, assuming its text can be understood as an integer, and decreases
     * the number in it by one.
     *
     * @param v TextView
     * @return the new number in the text view
     */

    private int incrementScore(TextView v) {
        int currentScore = Integer.parseInt(v.getText().toString());
        v.setText(String.valueOf(currentScore + 1));
        return currentScore + 1;
    }

    /**
     * Takes any {@link TextView}}, assuming its text can be understood as an integer greater than
     * 0, and decreases the number in it by one. Does not allow negative numbers.
     *
     * @param v TextView
     * @return the new number in the text view
     */
    private int decrementScore(TextView v) {
        int currentScore = Integer.parseInt(v.getText().toString());
        // No negative scores
        if (currentScore > 0) {
            v.setText(String.valueOf(currentScore - 1));
            return currentScore - 1;
        }
        return currentScore;
    }

}
