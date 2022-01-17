package com.sidparikh.materialuserapp.matchactivity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sidparikh.materialuserapp.R;
import com.sidparikh.materialuserapp.matchactivity.CounterView;
import com.sidparikh.materialuserapp.matchactivity.MatchActivity;

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

        // MatchActivity will handle the counters. This makes it easy to save the data in one place
        ((CounterView) view.findViewById(R.id.counter_auto_inner)).setScoreChangeListener(matchActivity);
        ((CounterView) view.findViewById(R.id.counter_auto_outer)).setScoreChangeListener(matchActivity);
        ((CounterView) view.findViewById(R.id.counter_auto_lower)).setScoreChangeListener(matchActivity);
        ((CounterView) view.findViewById(R.id.counter_auto_pickup)).setScoreChangeListener(matchActivity);

        matchActivity.addFreezableLayout(view.findViewById(R.id.layout_auto_freezable));

    }
}
