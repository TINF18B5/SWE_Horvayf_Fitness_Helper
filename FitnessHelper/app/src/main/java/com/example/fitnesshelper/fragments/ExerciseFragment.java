package com.example.fitnesshelper.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesshelper.R;
import com.example.fitnesshelper.adapter.ExerciseAdapter;
import com.example.fitnesshelper.adapter.MusclePlanAdapter;
import com.example.fitnesshelper.db.DataBaseConnector;
import com.example.fitnesshelper.db.model.Exercise;


public class ExerciseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_exercise);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter();
        DataBaseConnector.getInstance().getAllExercises().observe(requireActivity(), exerciseAdapter::setExercises);
        recyclerView.setAdapter(exerciseAdapter);
        return view;
    }
}