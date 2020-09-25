package com.example.fitnesshelper.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.fitnesshelper.MainActivity;
import com.example.fitnesshelper.R;

public class PlanFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).setActionBarTitle("Pläne");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        ImageButton muscleBtn = view.findViewById(R.id.imageButton_muscle_plans);
        ImageButton cardioBtn = view.findViewById(R.id.imageButton_cardio_plans);
        ImageButton trainingBtn = view.findViewById(R.id.imageButton_training_plans);
        MusclePlanFragment musclePlanFragment = new MusclePlanFragment();
        CardioPlanFragment cardioPlanFragment = new CardioPlanFragment();
        TrainingPlanFragment trainingPlanFragment = new TrainingPlanFragment();

        //Change Fragment if Imagebutton is clicked
        muscleBtn.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, musclePlanFragment).addToBackStack(null).commit());
        cardioBtn.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, cardioPlanFragment).addToBackStack(null).commit());
        trainingBtn.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, trainingPlanFragment).addToBackStack(null).commit());
        return view;
    }
}