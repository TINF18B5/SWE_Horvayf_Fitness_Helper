package com.example.fitnesshelper.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "trainingplan")
public class TrainingPlan {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trainingplan_id")
    private int trainingplanID;
    private String name;
    private String description;
    private Date erstellungsdatum;

    public TrainingPlan(int trainingplanID, String name, String description, Date erstellungsdatum) {
        this.trainingplanID = trainingplanID;
        this.name = name;
        this.description = description;
        this.erstellungsdatum = erstellungsdatum;
    }

    @Ignore
    public TrainingPlan(String name, String description, Date erstellungsdatum) {
        this.name = name;
        this.description = description;
        this.erstellungsdatum = erstellungsdatum;
    }

    public int getTrainingplanID() {
        return trainingplanID;
    }

    public void setTrainingplanID(int trainingplanID) {
        this.trainingplanID = trainingplanID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(Date erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }
}
