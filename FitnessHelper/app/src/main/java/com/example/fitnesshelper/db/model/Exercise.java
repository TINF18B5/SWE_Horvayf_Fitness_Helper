package com.example.fitnesshelper.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id")
    private int exerciseID;
    private String name;
    private String description;
    private String type;
    private String imgID;

    public Exercise(int exerciseID, String name, String description, String type, String imgID) {
        this.exerciseID = exerciseID;
        this.name = name;
        this.description = description;
        this.type = type;
        this.imgID = imgID;
    }

    @Ignore
    public Exercise(String name, String description, String type, String imgID) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.imgID = imgID;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgID() {
        return imgID;
    }

    public void setImgID(String imgID) {
        this.imgID = imgID;
    }
}
