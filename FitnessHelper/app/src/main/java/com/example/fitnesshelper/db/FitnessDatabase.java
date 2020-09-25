package com.example.fitnesshelper.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fitnesshelper.db.dao.CardioPlanDAO;
import com.example.fitnesshelper.db.dao.DateDAO;
import com.example.fitnesshelper.db.dao.ExerciseDAO;
import com.example.fitnesshelper.db.dao.MusclePlanDAO;
import com.example.fitnesshelper.db.dao.TrainingPlanDAO;
import com.example.fitnesshelper.db.dao.UserDAO;
import com.example.fitnesshelper.db.model.CardioPlan;
import com.example.fitnesshelper.db.model.Date;
import com.example.fitnesshelper.db.model.Exercise;
import com.example.fitnesshelper.db.model.MusclePlan;
import com.example.fitnesshelper.db.model.TrainingPlan;
import com.example.fitnesshelper.db.model.User;

@Database(entities = {User.class, Date.class, MusclePlan.class, CardioPlan.class, TrainingPlan.class, Exercise.class}, version = 1, exportSchema = false)
@TypeConverters({DataBaseConverter.class})
public abstract class FitnessDatabase extends RoomDatabase {
    private static final String DB_NAME = "fitness_db";
    private static FitnessDatabase instance;

    public static synchronized FitnessDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FitnessDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();

    public abstract DateDAO dateDAO();

    public abstract MusclePlanDAO musclePlanDAO();

    public abstract CardioPlanDAO cardioPlanDAO();

    public abstract TrainingPlanDAO trainingPlanDAO();

    public abstract ExerciseDAO exerciseDAO();
}
