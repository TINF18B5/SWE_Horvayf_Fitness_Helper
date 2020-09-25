package com.example.fitnesshelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesshelper.R;
import com.example.fitnesshelper.db.model.Date;
import com.example.fitnesshelper.db.model.User;

import java.util.ArrayList;
import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.NoteHolder> {


    private List<Date> dates = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView itemView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item, parent, false);
        return new DateAdapter.NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.NoteHolder holder, int position) {
        Date currentDate = dates.get(position);
        holder.textViewID.setText(String.format("ID: %s",String.valueOf(currentDate.getDateID())));
        holder.textViewName.setText(currentDate.getName());
        holder.textViewDescription.setText(String.format("Beschreibung: %s", currentDate.getDescription()));
        holder.textViewWeight.setText(String.format("Gewicht: %s" ,currentDate.getWeight()));
        holder.textViewCalories.setText(String.format("Kalorien: %s", currentDate.getCalories()));
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public void setDates(List<Date> dates){
        this.dates=dates;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewID;
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewWeight;
        private TextView textViewCalories;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_view_calendar_entry_id);
            textViewName = itemView.findViewById(R.id.text_view_calendar_entry_name);
            textViewDescription  = itemView.findViewById(R.id.text_view_calendar_entry_description);
            textViewWeight = itemView.findViewById(R.id.text_view_calendar_entry_weight);
            textViewCalories = itemView.findViewById(R.id.text_view_calendar_entry_calories);

        }
    }
}
