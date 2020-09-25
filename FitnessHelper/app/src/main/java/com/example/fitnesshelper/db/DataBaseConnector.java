package com.example.fitnesshelper.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;

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

import java.util.List;

public class DataBaseConnector {
    private static @NonNull
    DataBaseConnector INSTANCE;

    static {
        INSTANCE = new DataBaseConnector();
    }

    //Connections
    private UserDAO userDAO;
    private DateDAO dateDAO;
    private MusclePlanDAO musclePlanDAO;
    private CardioPlanDAO cardioPlanDAO;
    private TrainingPlanDAO trainingPlanDAO;
    private ExerciseDAO exerciseDAO;

    public static DataBaseConnector getInstance() {
        return INSTANCE;
    }

    public void init(Application application) {
        FitnessDatabase database = FitnessDatabase.getInstance(application);
        userDAO = database.userDAO();
        dateDAO = database.dateDAO();
        musclePlanDAO = database.musclePlanDAO();
        cardioPlanDAO = database.cardioPlanDAO();
        trainingPlanDAO = database.trainingPlanDAO();
        exerciseDAO = database.exerciseDAO();
    }


    //DB Actions for User
    public void insert(User user) {
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDAO).execute(user);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDAO).execute(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getUserList();
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insertUser(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private UpdateUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.updateUser(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private DeleteUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.deleteUser(users[0]);
            return null;
        }
    }


    //DB Actions for Date
    public void insert(Date date) {
        new InsertDateAsyncTask(dateDAO).execute(date);
    }

    public void update(Date date) {
        new UpdateDateAsyncTask(dateDAO).execute(date);
    }

    public void delete(Date date) {
        new DeleteDateAsyncTask(dateDAO).execute(date);
    }

    public LiveData<List<Date>> getAllDates() {
        return dateDAO.getDateList();
    }

    private static class InsertDateAsyncTask extends AsyncTask<Date, Void, Void> {
        private DateDAO dateDAO;

        private InsertDateAsyncTask(DateDAO dateDAO) {
            this.dateDAO = dateDAO;
        }

        @Override
        protected Void doInBackground(Date... dates) {
            dateDAO.insertDate(dates[0]);
            return null;
        }
    }

    private static class UpdateDateAsyncTask extends AsyncTask<Date, Void, Void> {
        private DateDAO dateDAO;

        private UpdateDateAsyncTask(DateDAO dateDAO) {
            this.dateDAO = dateDAO;
        }

        @Override
        protected Void doInBackground(Date... dates) {
            dateDAO.updateDate(dates[0]);
            return null;
        }
    }

    private static class DeleteDateAsyncTask extends AsyncTask<Date, Void, Void> {
        private DateDAO dateDAO;

        private DeleteDateAsyncTask(DateDAO dateDAO) {
            this.dateDAO = dateDAO;
        }

        @Override
        protected Void doInBackground(Date... dates) {
            dateDAO.deleteDate(dates[0]);
            return null;
        }
    }

    //DB Actions for MusclePlans
    public void insert(MusclePlan musclePlan) {
        new InsertMusclePlanAsyncTask(musclePlanDAO).execute(musclePlan);
    }

    public void update(MusclePlan musclePlan) {
        new UpdateMusclePlanAsyncTask(musclePlanDAO).execute(musclePlan);
    }

    public void delete(MusclePlan musclePlan) {
        new DeleteMusclePlanAsyncTask(musclePlanDAO).execute(musclePlan);
    }

    public LiveData<List<MusclePlan>> getAllMusclePlans() {
        return musclePlanDAO.getMusclePlanList();
    }

    private static class InsertMusclePlanAsyncTask extends AsyncTask<MusclePlan, Void, Void> {
        private MusclePlanDAO musclePlanDAO;

        private InsertMusclePlanAsyncTask(MusclePlanDAO musclePlanDAO) {
            this.musclePlanDAO = musclePlanDAO;
        }

        @Override
        protected Void doInBackground(MusclePlan... musclePlans) {
            musclePlanDAO.insertMusclePlan(musclePlans[0]);
            return null;
        }
    }

    private static class UpdateMusclePlanAsyncTask extends AsyncTask<MusclePlan, Void, Void> {
        private MusclePlanDAO musclePlanDAO;

        private UpdateMusclePlanAsyncTask(MusclePlanDAO musclePlanDAO) {
            this.musclePlanDAO = musclePlanDAO;
        }

        @Override
        protected Void doInBackground(MusclePlan... musclePlans) {
            musclePlanDAO.updateMusclePlan(musclePlans[0]);
            return null;
        }
    }

    private static class DeleteMusclePlanAsyncTask extends AsyncTask<MusclePlan, Void, Void> {
        private MusclePlanDAO musclePlanDAO;

        private DeleteMusclePlanAsyncTask(MusclePlanDAO musclePlanDAO) {
            this.musclePlanDAO = musclePlanDAO;
        }

        @Override
        protected Void doInBackground(MusclePlan... musclePlans) {
            musclePlanDAO.deleteMusclePlan(musclePlans[0]);
            return null;
        }
    }

    //DB Actions for CardioPlans
    public void insert(CardioPlan cardioPlan) {
        new InsertCardioPlanAsyncTask(cardioPlanDAO).execute(cardioPlan);
    }

    public void update(CardioPlan cardioPlan) {
        new UpdateCardioPlanAsyncTask(cardioPlanDAO).execute(cardioPlan);
    }

    public void delete(CardioPlan cardioPlan) {
        new DeleteCardioPlanAsyncTask(cardioPlanDAO).execute(cardioPlan);
    }

    public LiveData<List<CardioPlan>> getAllCardioPlans() {
        return cardioPlanDAO.getCardioPlanList();
    }

    private static class InsertCardioPlanAsyncTask extends AsyncTask<CardioPlan, Void, Void> {
        private CardioPlanDAO cardioPlanDAO;

        private InsertCardioPlanAsyncTask(CardioPlanDAO cardioPlanDAO) {
            this.cardioPlanDAO = cardioPlanDAO;
        }

        @Override
        protected Void doInBackground(CardioPlan... cardioPlans) {
            cardioPlanDAO.insertCardioPlan(cardioPlans[0]);
            return null;
        }
    }

    private static class UpdateCardioPlanAsyncTask extends AsyncTask<CardioPlan, Void, Void> {
        private CardioPlanDAO cardioPlanDAO;

        private UpdateCardioPlanAsyncTask(CardioPlanDAO cardioPlanDAO) {
            this.cardioPlanDAO = cardioPlanDAO;
        }

        @Override
        protected Void doInBackground(CardioPlan... cardioPlans) {
            cardioPlanDAO.updateCardioPlan(cardioPlans[0]);
            return null;
        }
    }

    private static class DeleteCardioPlanAsyncTask extends AsyncTask<CardioPlan, Void, Void> {
        private CardioPlanDAO cardioPlanDAO;

        private DeleteCardioPlanAsyncTask(CardioPlanDAO cardioPlanDAO) {
            this.cardioPlanDAO = cardioPlanDAO;
        }

        @Override
        protected Void doInBackground(CardioPlan... cardioPlans) {
            cardioPlanDAO.deleteCardioPlan(cardioPlans[0]);
            return null;
        }
    }


    //DB Actions for TrainingsPlans
    public void insert(TrainingPlan trainingPlan) {
        new InsertTrainingPlanAsyncTask(trainingPlanDAO).execute(trainingPlan);
    }

    public void update(TrainingPlan trainingPlan) {
        new UpdateTrainingPlanAsyncTask(trainingPlanDAO).execute(trainingPlan);
    }

    public void delete(TrainingPlan trainingPlan) {
        new DeleteTrainingPlanAsyncTask(trainingPlanDAO).execute(trainingPlan);
    }

    public LiveData<List<TrainingPlan>> getAllTrainingPlans() {
        return trainingPlanDAO.getTrainingPlanList();
    }

    private static class InsertTrainingPlanAsyncTask extends AsyncTask<TrainingPlan, Void, Void> {
        private TrainingPlanDAO trainingPlanDAO;

        private InsertTrainingPlanAsyncTask(TrainingPlanDAO trainingPlanDAO) {
            this.trainingPlanDAO = trainingPlanDAO;
        }

        @Override
        protected Void doInBackground(TrainingPlan... trainingPlans) {
            trainingPlanDAO.insertTrainingPlan(trainingPlans[0]);
            return null;
        }
    }

    private static class UpdateTrainingPlanAsyncTask extends AsyncTask<TrainingPlan, Void, Void> {
        private TrainingPlanDAO trainingPlanDAO;

        private UpdateTrainingPlanAsyncTask(TrainingPlanDAO trainingPlanDAO) {
            this.trainingPlanDAO = trainingPlanDAO;
        }

        @Override
        protected Void doInBackground(TrainingPlan... trainingPlans) {
            trainingPlanDAO.updateTrainingPlan(trainingPlans[0]);
            return null;
        }
    }

    private static class DeleteTrainingPlanAsyncTask extends AsyncTask<TrainingPlan, Void, Void> {
        private TrainingPlanDAO trainingPlanDAO;

        private DeleteTrainingPlanAsyncTask(TrainingPlanDAO trainingPlanDAO) {
            this.trainingPlanDAO = trainingPlanDAO;
        }

        @Override
        protected Void doInBackground(TrainingPlan... trainingPlans) {
            trainingPlanDAO.deleteTrainingPlan(trainingPlans[0]);
            return null;
        }
    }

    //DB Actions for Exercises
    public void insert(Exercise exercise) {
        new InsertExerciseAsyncTask(exerciseDAO).execute(exercise);
    }

    public void update(Exercise exercise) {
        new UpdateExerciseAsyncTask(exerciseDAO).execute(exercise);
    }

    public void delete(Exercise exercise) {
        new DeleteExerciseAsyncTask(exerciseDAO).execute(exercise);
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return exerciseDAO.getExerciseList();
    }

    private static class InsertExerciseAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDAO exerciseDAO;

        private InsertExerciseAsyncTask(ExerciseDAO exerciseDAO) {
            this.exerciseDAO = exerciseDAO;
        }

        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDAO.insertExercise(exercises[0]);
            return null;
        }
    }

    private static class UpdateExerciseAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDAO exerciseDAO;

        private UpdateExerciseAsyncTask(ExerciseDAO exerciseDAO) {
            this.exerciseDAO = exerciseDAO;
        }

        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDAO.updateExercise(exercises[0]);
            return null;
        }
    }

    private static class DeleteExerciseAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDAO exerciseDAO;

        private DeleteExerciseAsyncTask(ExerciseDAO exerciseDAO) {
            this.exerciseDAO = exerciseDAO;
        }

        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDAO.deleteExercise(exercises[0]);
            return null;
        }
    }
}
