package com.example.fitnesshelper.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.fitnesshelper.db.model.CardioPlan;
import java.util.List;

@Dao
public interface CardioPlanDAO {

    @Query("Select * from cardioplan")
    LiveData<List<CardioPlan>> getCardioPlanList();

    @Insert
    void insertCardioPlan(CardioPlan cardioPlan);

    @Update
    void updateCardioPlan(CardioPlan cardioPlan);

    @Delete
    void deleteCardioPlan(CardioPlan cardioPlan);
}
