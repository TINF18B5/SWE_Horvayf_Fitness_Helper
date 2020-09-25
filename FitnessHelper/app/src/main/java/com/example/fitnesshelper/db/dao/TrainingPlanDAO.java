package com.example.fitnesshelper.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnesshelper.db.model.CardioPlan;
import com.example.fitnesshelper.db.model.TrainingPlan;

import java.util.List;

@Dao
public interface TrainingPlanDAO {

    @Query("Select * from trainingplan")
    LiveData<List<TrainingPlan>> getTrainingPlanList();

    @Insert
    void insertTrainingPlan(TrainingPlan trainingPlan);

    @Update
    void updateTrainingPlan(TrainingPlan trainingPlan);

    @Delete
    void deleteTrainingPlan(TrainingPlan trainingPlan);
}
