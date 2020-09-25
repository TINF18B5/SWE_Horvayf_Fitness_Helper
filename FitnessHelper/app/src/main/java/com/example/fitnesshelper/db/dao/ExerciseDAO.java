package com.example.fitnesshelper.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnesshelper.db.model.CardioPlan;
import com.example.fitnesshelper.db.model.Exercise;

import java.util.List;

@Dao
public interface ExerciseDAO {

    @Query("Select * from exercise")
    LiveData<List<Exercise>> getExerciseList();

    @Insert
    void insertExercise(Exercise exercise);

    @Update
    void updateExercise(Exercise exercise);

    @Delete
    void deleteExercise(Exercise exercise);
}
