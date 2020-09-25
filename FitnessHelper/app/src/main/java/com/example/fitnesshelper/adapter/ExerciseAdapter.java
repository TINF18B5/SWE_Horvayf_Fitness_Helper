package com.example.fitnesshelper.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesshelper.R;
import com.example.fitnesshelper.db.model.CardioPlan;
import com.example.fitnesshelper.db.model.Exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.NoteHolder> {


    private List<Exercise> exercises = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView itemView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, parent, false);
        return new ExerciseAdapter.NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.NoteHolder holder, int position) {
        Exercise currentExercise = exercises.get(position);
        holder.textViewName.setText(currentExercise.getName());
        holder.textViewDescription.setText(currentExercise.getDescription());
        holder.textViewType.setText(String.format("Art der Übung: %s", currentExercise.getType()));
        if (currentExercise.getImgID().contains("bicycle")){
            holder.imageViewExerciseImg.setImageResource(R.drawable.img_bicycle);
        }else if (currentExercise.getImgID().contains("crunches")){
            holder.imageViewExerciseImg.setImageResource(R.drawable.img_crunches);
        }else if (currentExercise.getImgID().contains("liege")){
            holder.imageViewExerciseImg.setImageResource(R.drawable.img_liegestuetze);
        }else if (currentExercise.getImgID().contains("situp")){
            holder.imageViewExerciseImg.setImageResource(R.drawable.img_situps);
        }else if (currentExercise.getImgID().contains("hampel")){
            holder.imageViewExerciseImg.setImageResource(R.drawable.img_hampel);
        }

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercises(List<Exercise> exercises){
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewType;
        private ImageView imageViewExerciseImg;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_exercise_name);
            textViewDescription  = itemView.findViewById(R.id.text_view_exercise_description);
            textViewType = itemView.findViewById(R.id.text_view_exercise_type);
            imageViewExerciseImg = itemView.findViewById(R.id.imageView_exercise);
        }
    }
}
