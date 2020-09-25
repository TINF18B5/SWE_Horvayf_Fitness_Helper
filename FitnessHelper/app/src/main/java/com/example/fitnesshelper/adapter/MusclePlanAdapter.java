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
import com.example.fitnesshelper.db.model.MusclePlan;
import com.example.fitnesshelper.fragments.ExerciseFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MusclePlanAdapter extends RecyclerView.Adapter<MusclePlanAdapter.NoteHolder> {


    private List<MusclePlan> musclePlans = new ArrayList<>();
    private CardView itemView;
    private Context context;

    public MusclePlanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.muscleplan_item, parent, false);
        return new MusclePlanAdapter.NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MusclePlanAdapter.NoteHolder holder, int position) {
        MusclePlan currentMusclePlan = musclePlans.get(position);
        holder.textViewID.setText(String.format("ID: %s", String.valueOf(currentMusclePlan.getMusclePlanID())));
        holder.textViewName.setText(currentMusclePlan.getName());
        holder.textViewDescription.setText(currentMusclePlan.getDescription());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        holder.textViewDate.setText(String.format("Erstellt: %s",df.format(currentMusclePlan.getErstellungsdatum())));
        itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Muskelaufbauplan \"" + currentMusclePlan.getName() + "\" geöffnet",Toast.LENGTH_SHORT).show();
            ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new ExerciseFragment()).addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return musclePlans.size();
    }

    public void setMusclePlans(List<MusclePlan> musclePlans){
        this.musclePlans = musclePlans;
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
