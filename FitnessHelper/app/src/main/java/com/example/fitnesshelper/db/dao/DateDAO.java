package com.example.fitnesshelper.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnesshelper.db.model.Date;
import com.example.fitnesshelper.db.model.User;

import java.util.List;

@Dao
public interface DateDAO {

    @Query("Select * from date")
    LiveData<List<Date>> getDateList();

    @Query("Select * from date WHERE date_id=:id")
    Date getDateByID(int id);

    @Insert
    void insertDate(Date date);

    @Update
    void updateDate(Date date);

    @Delete
    void deleteDate(Date date);
}
