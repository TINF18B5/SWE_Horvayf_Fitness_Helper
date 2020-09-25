package com.example.fitnesshelper.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "cardioplan")
public class CardioPlan {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cardioplan_id")
    private int cardioPlanID;
    private String name;
    private String description;
    private Date erstellungsdatum;

    public CardioPlan(int cardioPlanID, String name, String description, Date erstellungsdatum) {
        this.cardioPlanID = cardioPlanID;
        this.name = name;
        this.description = description;
        this.erstellungsdatum = erstellungsdatum;
    }

    @Ignore
    public CardioPlan(String name, String description, Date erstellungsdatum) {
        this.name = name;
        this.description = description;
        this.erstellungsdatum = erstellungsdatum;
    }

    public int getCardioPlanID() {
        return cardioPlanID;
    }

    public void setCardioPlanID(int cardioPlanID) {
        this.cardioPlanID = cardioPlanID;
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
