package com.example.fitnesshelper.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnesshelper.db.model.MusclePlan;

import java.util.List;

@Dao
public interface MusclePlanDAO {

    @Query("Select * from muscleplan")
    LiveData<List<MusclePlan>> getMusclePlanList();

    @Insert
    void insertMusclePlan(MusclePlan musclePlan);

    @Update
    void updateMusclePlan(MusclePlan musclePlan);

    @Delete
    void deleteMusclePlan(MusclePlan musclePlan);
}
