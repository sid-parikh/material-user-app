package com.sidparikh.materialuserapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;

public class AutoFragment extends Fragment {

    public AutoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MatchActivity matchActivity = (MatchActivity) requireActivity();

        view.findViewById(R.id.button_auto_lower_minus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_auto_lower_plus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_auto_outer_minus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_auto_outer_plus).setOnClickListener(matchActivity);
        //view.findViewById(R.id.button_auto_inner_minus).setOnClickListener(matchActivity);
        //view.findViewById(R.id.button_auto_inner_plus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_auto_pickup_minus).setOnClickListener(matchActivity);
        view.findViewById(R.id.button_auto_pickup_plus).setOnClickListener(matchActivity);
    }
}
