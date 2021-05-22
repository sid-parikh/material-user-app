package com.sidparikh.materialuserapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

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

        // MatchActivity will handle all of the incrementing and decrementing
        view.findViewById(R.id.button_teleop_inner_minus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_teleop_inner_plus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_teleop_outer_minus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_teleop_outer_plus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_teleop_lower_minus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_teleop_lower_plus).setOnClickListener(matchActivity);

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
