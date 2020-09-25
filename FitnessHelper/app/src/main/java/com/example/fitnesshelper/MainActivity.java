package com.example.fitnesshelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.DatabaseConfiguration;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ShareActionProvider;

import com.example.fitnesshelper.db.DataBaseConnector;
import com.example.fitnesshelper.db.model.CardioPlan;
import com.example.fitnesshelper.db.model.Exercise;
import com.example.fitnesshelper.db.model.MusclePlan;
import com.example.fitnesshelper.db.model.TrainingPlan;
import com.example.fitnesshelper.db.model.User;
import com.example.fitnesshelper.fragments.CalendarFragment;
import com.example.fitnesshelper.fragments.PlanFragment;
import com.example.fitnesshelper.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private CalendarFragment calFrag;
    private PlanFragment planFrag;
    private UserFragment userFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database Init
        DataBaseConnector.getInstance().init(getApplication());
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            fillDatabase();
        }


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_plan);
        frameLayout = findViewById(R.id.main_frame);
        calFrag = new CalendarFragment();
        planFrag = new PlanFragment();
        userFrag = new UserFragment();

        setFragment(planFrag, 0, 0);
        setNavigationListeners();
    }

    public boolean fillDatabase() {
        //DB Premade Values
        DataBaseConnector.getInstance().insert(new MusclePlan("Montag Muskelaufbau", "Fitnessübungen für Montags", new Date()));
        DataBaseConnector.getInstance().insert(new MusclePlan("Dienstag Muskelaufbau", "Fitnessübungen für Mittwochs", new Date()));
        DataBaseConnector.getInstance().insert(new MusclePlan("Mittwoch Muskelaufbau", "Fitnessübungen für Mittwochs", new Date()));
        DataBaseConnector.getInstance().insert(new MusclePlan("Donnerstag Muskelaufbau", "Fitnessübungen für Mittwochs", new Date()));
        DataBaseConnector.getInstance().insert(new MusclePlan("Freitag Muskelaufbau", "Fitnessübungen für Freitags", new Date()));
        DataBaseConnector.getInstance().insert(new TrainingPlan("Do what you want Montag", "Alle Möglichen Übungen auf die ich bock hab", new Date()));
        DataBaseConnector.getInstance().insert(new TrainingPlan("Do what you want Dienstag", "Alle Möglichen Übungen auf die ich bock hab", new Date()));
        DataBaseConnector.getInstance().insert(new TrainingPlan("Do what you want Mittwoch", "Alle Möglichen Übungen auf die ich bock hab", new Date()));
        DataBaseConnector.getInstance().insert(new TrainingPlan("Dunkins Donnerstag", "Dunkin Donouts are the best", new Date()));
        DataBaseConnector.getInstance().insert(new TrainingPlan("Do what you want Freitag", "Alle Möglichen Übungen auf die ich bock hab", new Date()));
        DataBaseConnector.getInstance().insert(new TrainingPlan("Samstag relaxed", "Zum runterkommen", new Date()));
        DataBaseConnector.getInstance().insert(new CardioPlan("Cardio am Montag", "Bisschen laufen gehen", new Date()));
        DataBaseConnector.getInstance().insert(new CardioPlan("Cardio am Dienstag", "Bisschen laufen gehen", new Date()));
        DataBaseConnector.getInstance().insert(new CardioPlan("Cardio am Mittwoch", "Bisschen laufen gehen", new Date()));
        DataBaseConnector.getInstance().insert(new CardioPlan("Cardio am Donnerstag", "Bisschen laufen gehen", new Date()));
        DataBaseConnector.getInstance().insert(new CardioPlan("Cardio am Freitag", "Bisschen laufen gehen", new Date()));
        DataBaseConnector.getInstance().insert(new CardioPlan("Cardio am Samstag", "Bisschen laufen gehen", new Date()));
        DataBaseConnector.getInstance().insert(new CardioPlan("Cardio am Sonntag", "Bisschen laufen gehen", new Date()));
        DataBaseConnector.getInstance().insert(new com.example.fitnesshelper.db.model.Date(new Date(), "Start meiner Sportlerkarriere", "Probieren Sie gerne aus einen Eintrag anzulegen", 2000, 70));
        DataBaseConnector.getInstance().insert(new User("Ferenc", "Horvay", 68, 186, "Männlich"));
        DataBaseConnector.getInstance().insert(new Exercise("Bicycle Crunches", "Die Beine in der Luft abwechselnd anziehen und dabei den Oberkörper diagonal zum Knie drehen.", "Muskelaufbau", "bicycle"));
        DataBaseConnector.getInstance().insert(new Exercise("Crunches", "Hebe deine Arme, den Kopf und den oberen Rücken einige Zentimeter vom Boden ab und senke sie dann wieder ab, ohne sie ganz abzulegen.", "Muskelaufbau", "crunches"));
        DataBaseConnector.getInstance().insert(new Exercise("Hampelmann", "Führe deine Arme und Beine gleichzeitig jeweils zusammen oder auseinander, während du leicht in die Luft springst.", "Ausdauer", "hampel"));
        DataBaseConnector.getInstance().insert(new Exercise("Liegestütze", "Lasse den Körper möglichst weit zum Boden sinken und drücke den Körper, bis kurz bevor die Arme vollständig gestreckt sind, hoch.", "Muskelaufbau", "liege"));
        DataBaseConnector.getInstance().insert(new Exercise("Sit Up's", "Beuge den Oberkörper und hebe mit dem Rücken vom Boden ab. Atme dabei aus. Kehre in die Ausgangsstellung zurück und atme ein.", "Muskelaufbau", "situp"));


        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
        return true;
    }


    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void setNavigationListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_calendar:
                        return navLeft(calFrag);
                    case R.id.nav_plan:
                        if (bottomNavigationView.getSelectedItemId() == R.id.nav_calendar) {
                            return navRight(planFrag);
                        } else {
                            return navLeft(planFrag);
                        }
                    case R.id.nav_user:
                        return navRight(userFrag);
                    default:
                        return false;
                }
            }
        });
    }

    private boolean navRight(Fragment destination) {
        return setFragment(destination, R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private boolean navLeft(Fragment destination) {
        return setFragment(destination, R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private boolean setFragment(Fragment fragment, int inAnim, int outAnim) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(inAnim, outAnim);
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
        return true;
    }


}