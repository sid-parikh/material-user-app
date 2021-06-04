package com.sidparikh.materialuserapp.matchactivity.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.sidparikh.materialuserapp.R;
import com.sidparikh.materialuserapp.matchactivity.CounterView;
import com.sidparikh.materialuserapp.matchactivity.MatchActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeleopFragment extends Fragment {

    public TeleopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MatchActivity matchActivity = (MatchActivity) requireActivity();

        // MatchActivity will handle the counters. This makes it easy to save the data in one place
        ((CounterView) view.findViewById(R.id.counter_teleop_inner)).setScoreChangeListener(matchActivity);
        ((CounterView) view.findViewById(R.id.counter_teleop_outer)).setScoreChangeListener(matchActivity);
        ((CounterView) view.findViewById(R.id.counter_teleop_lower)).setScoreChangeListener(matchActivity);

        // MatchActivity will also handle the checkboxes. This makes it easy to save the data
        CheckBox positionCheckBox = view.findViewById(R.id.check_position);
        CheckBox rotationChecKBox = view.findViewById(R.id.check_rotation);

        positionCheckBox.setOnCheckedChangeListener(matchActivity);
        rotationChecKBox.setOnCheckedChangeListener(matchActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teleop, container, false);
    }
}
