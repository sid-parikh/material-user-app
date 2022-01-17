package com.sidparikh.materialuserapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.sidparikh.materialuserapp.matchactivity.MatchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MATCH_EXTRA = "com.sidparikh.materialuserapp.intent.matchnumber";
    public static final String TEAM_EXTRA = "com.sidparikh.materialuserapp.intent.teamnumber";
    public static final String ALLIANCE_EXTRA = "com.sidparikh.materialuserapp.intent.alliance";

    private TextInputLayout mTeamTextLayout;
    private TextInputLayout mMatchTextLayout;
    private MaterialButtonToggleGroup mAllianceToggle;
    private MaterialButton mRedButton;
    private MaterialButton mBlueButton;
    private TextView mToggleErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Storm);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle all buttons in the onClick method of this class.
        findViewById(R.id.button_delete).setOnClickListener(this);
        findViewById(R.id.button_start).setOnClickListener(this);

        mTeamTextLayout = findViewById(R.id.text_layout_team);
        mMatchTextLayout = findViewById(R.id.text_layout_match);
        mAllianceToggle = findViewById(R.id.toggle_alliance);
        mRedButton = findViewById(R.id.button_red);
        mBlueButton = findViewById(R.id.button_blue);
        mToggleErrorText = findViewById(R.id.text_toggle_error);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        // Unknown; allow superclass to handle
        return super.onOptionsItemSelected(item);
    }

    /**
     * Handles all button clicks for this Activity. Overrides {@link View.OnClickListener}
     *
     * @param v The view (button) that was clicked
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_delete) {
        } else if (id == R.id.button_start) {
            start();
        }
    }

    /**
     * Checks for errors, collects entered data, and starts {@link MatchActivity}.
     */
    private void start() {
        // Check Match and Team for errors.
        boolean teamError = checkNumberTextInput(mTeamTextLayout, getString(R.string.error_teamnum_missing));
        boolean matchError = checkNumberTextInput(mMatchTextLayout, getString(R.string.error_matchnum_missing));
        boolean allianceError = checkToggleGroup();

        // Only continue if there are no errors.
        if (teamError || matchError || allianceError) {
            Toast.makeText(this, getString(R.string.toast_errors), Toast.LENGTH_SHORT).show();
        } else {
            // Collect the entered data.
            int team = Integer.parseInt(mTeamTextLayout.getEditText().getText().toString());
            int match = Integer.parseInt(mMatchTextLayout.getEditText().getText().toString());

            // Alliance is true if red, false if blue.
            boolean alliance = mAllianceToggle.getCheckedButtonId() == mRedButton.getId();

            // Pass the data to MatchActivity.
            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra(TEAM_EXTRA, team);
            intent.putExtra(MATCH_EXTRA, match);
            intent.putExtra(ALLIANCE_EXTRA, alliance);
            startActivity(intent);
        }
    }

    /**
     * Checks that the alliance ToggleGroup is not empty and displays an error if it is.
     *
     * @return true if the group is empty
     */
    private boolean checkToggleGroup() {
        boolean errored = false;
        int checkedButton = mAllianceToggle.getCheckedButtonId();

        if (checkedButton == View.NO_ID) {
            // No alliance is selected
            errored = true;

            // Get theme's error color
            TypedValue tv = new TypedValue();
            Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorError, tv, true);
            @ColorInt int color = tv.data;

            // Save the old stroke color
            final ColorStateList oldColor = mRedButton.getStrokeColor();

            // Set the stroke color to the outline color
            mRedButton.setStrokeColor(ColorStateList.valueOf(color));
            mBlueButton.setStrokeColor(ColorStateList.valueOf(color));

            // Display error text
            mToggleErrorText.setText(R.string.error_alliance_missing);
            mToggleErrorText.setVisibility(View.VISIBLE);

            // Reset the ToggleGroup once an item is selected
            mAllianceToggle.addOnButtonCheckedListener(
                    new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                        @Override
                        public void onButtonChecked(MaterialButtonToggleGroup group,
                                                    int checkedId, boolean isChecked) {
                            mRedButton.setStrokeColor(oldColor);
                            mBlueButton.setStrokeColor(oldColor);
                            mToggleErrorText.setText(R.string.empty);
                            mToggleErrorText.setVisibility(View.GONE);
                            mAllianceToggle.removeOnButtonCheckedListener(this);
                        }
                    });
        }
        return errored;
    }

    /**
     * Checks for and displays errors on a given {@link TextInputLayout} and creates a watcher to
     * dismiss the error once it is corrected.
     * <p>
     * The parameters for validation are that the entered input must be 1. Not Empty 2. Digits Only
     * 3. Less than the set max length, if one is set as an attribute of the TextInputLayout.
     *
     * @param textInputLayout      TextInputLayout to monitor
     * @param contextualEmptyError The error message to be displayed if this TextInputLayout is empty.
     * @return whether or not an error was found
     */
    public boolean checkNumberTextInput(final TextInputLayout textInputLayout, String contextualEmptyError) {
        boolean isError = false;
        final EditText editText = textInputLayout.getEditText();

        // This shouldn't happen but TextInputLayout#getEditText() is marked as nullable.
        if (editText == null) {
            return true;
        }
        String input = editText.getText().toString();

        /* Check to make sure the entered text exists, is a number, and is shorter than
           the set max length */
        if (TextUtils.isEmpty(input)) {
            isError = true;
            textInputLayout.setError(contextualEmptyError);
        } else if (!TextUtils.isDigitsOnly(input)) {
            isError = true;
            textInputLayout.setError(getString(R.string.error_notnumber));
        } else if (textInputLayout.getCounterMaxLength() > 0 && input.length() > textInputLayout.getCounterMaxLength()) {
            isError = true;
            textInputLayout.setError(getString(R.string.error_toolong));
        }

        //  If errors were found, add a listener to dismiss the error once it is corrected
        if (isError) {
            editText.addTextChangedListener(new SmallerTextWatcher(textInputLayout, editText) {
                @Override
                public void afterTextChanged(String input, TextInputLayout layout,
                                             EditText editText) {
                    /* Text is not errored if:
                       1. Not Empty
                       2. Digits Only
                       3. Either there is no max or length is <= max
                     */
                    if ((!TextUtils.isEmpty(input) && TextUtils.isDigitsOnly(
                            input)) && (layout.getCounterMaxLength() <= 0 || input.length() <= layout.getCounterMaxLength())) {
                        layout.setError(null);
                        editText.removeTextChangedListener(this);
                    }
                }
            });
        }

        return isError;
    }

    /**
     * Version of {@link MainActivity#checkNumberTextInput(TextInputLayout, String)} that uses a
     * default empty message.
     *
     * @param textInputLayout TextInputLayout to monitor
     * @return whether or not an error was found
     */
    public boolean checkNumberTextInput(final TextInputLayout textInputLayout) {
        return checkNumberTextInput(textInputLayout, getString(R.string.error_field_empty));
    }

    /**
     * A smaller version of TextWatcher that automatically implements the two methods that are
     * usually unused. This is a utility to reduce boilerplate code in the main methods.
     */
    private abstract static class SmallerTextWatcher implements TextWatcher {
        private final TextInputLayout mTextInputLayout;
        private final EditText mEditText;

        SmallerTextWatcher(TextInputLayout l, EditText e) {
            mTextInputLayout = l;
            mEditText = e;
        }

        @Override
        public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Do nothing
        }

        @Override
        public final void onTextChanged(CharSequence s, int start, int before, int count) {
            // Do nothing here as it interrupts the user. Prefer afterTextChanged.
        }

        @Override
        public final void afterTextChanged(Editable s) {
            // Defer to a more useful method
            afterTextChanged(s.toString(), mTextInputLayout, mEditText);
        }

        public abstract void afterTextChanged(String input, TextInputLayout layout,
                                              EditText editText);
    }

}
