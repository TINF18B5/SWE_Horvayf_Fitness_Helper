package com.example.fitnesshelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesshelper.R;
import com.example.fitnesshelper.db.model.TrainingPlan;
import com.example.fitnesshelper.fragments.ExerciseFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TrainingPlanAdapter extends RecyclerView.Adapter<TrainingPlanAdapter.NoteHolder> {


    private List<TrainingPlan> trainingPlans = new ArrayList<>();
    private CardView itemView;
    private Context context;

    public TrainingPlanAdapter(Context  context) {
        this.context=context;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.muscleplan_item, parent, false);
        return new TrainingPlanAdapter.NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingPlanAdapter.NoteHolder holder, int position) {
        TrainingPlan currentTrainingPlan = trainingPlans.get(position);
        holder.textViewID.setText(String.format("ID: %s", String.valueOf(currentTrainingPlan.getTrainingplanID())));
        holder.textViewName.setText(currentTrainingPlan.getName());
        holder.textViewDescription.setText(currentTrainingPlan.getDescription());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        holder.textViewDate.setText(String.format("Erstellt: %s", df.format(currentTrainingPlan.getErstellungsdatum())));
        itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Trainingsplan \"" + currentTrainingPlan.getName() + "\" geöffnet",Toast.LENGTH_SHORT).show();
            ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new ExerciseFragment()).addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return trainingPlans.size();
    }

    public void setTrainingPlans(List<TrainingPlan> trainingPlans){
        this.trainingPlans = trainingPlans;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewID;
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewDate;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_view_muscleplan_entry_id);
            textViewName = itemView.findViewById(R.id.text_view_muscleplan_entry_name);
            textViewDescription  = itemView.findViewById(R.id.text_view_muscleplan_entry_description);
            textViewDate = itemView.findViewById(R.id.text_view_muscleplan_entry_date);

        }

    }

}
