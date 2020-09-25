package com.example.fitnesshelper.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "muscleplan")
public class MusclePlan {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "muscleplan_id")
    private int musclePlanID;
    private String name;
    private String description;
    private Date erstellungsdatum;

    public MusclePlan(int musclePlanID, String name, String description, Date erstellungsdatum) {
        this.musclePlanID = musclePlanID;
        this.name = name;
        this.description = description;
        this.erstellungsdatum = erstellungsdatum;
    }

    @Ignore
    public MusclePlan(String name, String description, Date erstellungsdatum) {
        this.name = name;
        this.description = description;
        this.erstellungsdatum = erstellungsdatum;
    }

    public int getMusclePlanID() {
        return musclePlanID;
    }

    public void setMusclePlanID(int musclePlanID) {
        this.musclePlanID = musclePlanID;
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
