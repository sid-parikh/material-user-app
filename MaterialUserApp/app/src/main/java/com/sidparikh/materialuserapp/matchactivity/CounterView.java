package com.sidparikh.materialuserapp.matchactivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.sidparikh.materialuserapp.R;

/**
 * This is a custom ViewGroup that is a self-contained Counter. Since we use many of these throughout
 * the app, this class consolidates the code to be re-used.
 */
public class CounterView extends ConstraintLayout implements View.OnClickListener {

    // These could eventually be member variables and XML attributes
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 99;

    private TextView mScoreView;
    private ScoreChangeListener mListener;

    public CounterView(Context context) {
        super(context);
        init(context);
    }

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.view_counter, this);
        mScoreView = findViewById(R.id.text_counter_score);

        Button minusButton = findViewById(R.id.button_counter_minus);
        Button plusButton = findViewById(R.id.button_counter_plus);

        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
    }

    public void setScoreChangeListener(ScoreChangeListener l) {
        mListener = l;
    }

    @Override
    public void onClick(View view) {
        int s = Integer.parseInt(mScoreView.getText().toString());
        if (view.getId() == R.id.button_counter_minus) {
            if (s > MIN_VALUE) {
                s--;
            }
        } else if (view.getId() == R.id.button_counter_plus) {
            if (s < MAX_VALUE) {
                s++;
            }
        }
        mScoreView.setText(String.valueOf(s));

        if (mListener != null) {
            mListener.onScoreChanged(this, s);
        }
    }

    public interface ScoreChangeListener {
        void onScoreChanged(CounterView counterView, int newScore);
    }
}
