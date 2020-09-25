package com.example.fitnesshelper.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "date")
public class Date {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "date_id")
    private int dateID;
    private java.util.Date date;
    private String name;
    private String description;
    private int calories;
    private int weight;

    public Date(int dateID, java.util.Date date, String name, String description, int calories, int weight) {
        this.dateID = dateID;
        this.date = date;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.weight = weight;
    }

    @Ignore
    public Date(java.util.Date date, String name, String description, int calories, int weight) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.weight = weight;
    }

    public int getDateID() {
        return dateID;
    }

    public void setDateID(int dateID) {
        this.dateID = dateID;
    }

    @NonNull
    public java.util.Date getDate() {
        return date;
    }

    public void setDate(@NonNull java.util.Date date) {
        this.date = date;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
