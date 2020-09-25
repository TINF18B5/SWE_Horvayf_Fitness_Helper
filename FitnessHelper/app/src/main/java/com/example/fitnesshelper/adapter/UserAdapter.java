package com.example.fitnesshelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesshelper.R;
import com.example.fitnesshelper.db.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.NoteHolder> {

    private List<User> users = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView itemView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        User currentUser = users.get(position);
        holder.textViewID.setText(String.format("ID: %s", String.valueOf(currentUser.getUserID())));
        holder.textViewName.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        holder.textViewWeight.setText(String.format("Gewicht in kg: %s", String.valueOf(currentUser.getWeight())));
        holder.textViewHeight.setText(String.format("Größe in cm: %s", String.valueOf(currentUser.getHeight())));
        holder.textViewSex.setText(String.format("Geschlecht: %s", currentUser.getSex()));
        double weight = currentUser.getWeight();
        double height = currentUser.getHeight();
        height = height/100;
        double bmi = weight/(height*height);
        holder.textViewBMI.setText(String.format("BMI: %3.4s", String.valueOf(bmi)));
        if (bmi < 20.0){
            holder.textViewBMIBewertung.setText("BMI Bewertung: Sind sind Untergewichtig");
        }else if (bmi < 24.9){
            holder.textViewBMIBewertung.setText("BMI Bewertung: Sie sind Normalgewichtig");
        }else if (bmi < 29.9){
            holder.textViewBMIBewertung.setText("BMI Bewertung: Sie sind Übergewichtig");
        }else if (bmi < 39.9){
            holder.textViewBMIBewertung.setText("BMI Bewertung: Sie sind Adipös");
        }else{
            holder.textViewBMIBewertung.setText("BMI Bewertung: Sie sind extrem Adipös");
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewID;
        private TextView textViewName;
        private TextView textViewWeight;
        private TextView textViewHeight;
        private TextView textViewSex;
        private TextView textViewBMI;
        private TextView textViewBMIBewertung;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_view_user_id);
            textViewName = itemView.findViewById(R.id.text_view_user_name);
            textViewWeight = itemView.findViewById(R.id.text_view_user_weight);
            textViewHeight = itemView.findViewById(R.id.text_view_user_height);
            textViewSex = itemView.findViewById(R.id.text_view_user_sex);
            textViewBMI = itemView.findViewById(R.id.text_view_user_bmi);
            textViewBMIBewertung=itemView.findViewById(R.id.text_view_user_bmi_text);
        }
    }
}
