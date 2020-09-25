package com.example.fitnesshelper.fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.fitnesshelper.MainActivity;
import com.example.fitnesshelper.R;
import com.example.fitnesshelper.adapter.DateAdapter;
import com.example.fitnesshelper.db.DataBaseConnector;
import com.example.fitnesshelper.db.model.Date;
import com.google.android.material.internal.NavigationMenu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class CalendarFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).setActionBarTitle("Kalender");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_calendar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DateAdapter dateAdapter = new DateAdapter();
        DataBaseConnector.getInstance().getAllDates().observe(requireActivity(), dateAdapter::setDates);
        recyclerView.setAdapter(dateAdapter);
        addFabSpeedDial(view);
        return view;
    }

    //Adds an FabSpeedDial and their Actionlisteners
    private void addFabSpeedDial(View view) {
        FabSpeedDial fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.button_speed_dial_calendar);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_date_add:
                        openAddEntryPopUpWindow(view);
                        break;
                    case R.id.action_date_delete:
                        openDeletePopUpWindow(view);
                        break;
                    case R.id.action_date_create_statistic:
                        Toast.makeText(getContext(),"Hier könnte ein Gewichtdiagramm angezeigt werden", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            @Override
            public void onMenuClosed() {

            }
        });
    }

    private void openAddEntryPopUpWindow(View view) {
        View popUpView = getLayoutInflater().inflate(R.layout.fragment_create_date, null);
        PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(100);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button cancelBtn = popUpView.findViewById(R.id.button_date_cancel_create);
        Button confirmBtn = popUpView.findViewById(R.id.button_date_confirm_create);
        //Close PopUpWindow on Button press
        cancelBtn.setOnClickListener(v -> popupWindow.dismiss());
        //Open DatePickerDialog to set Date
        final Calendar calendar = Calendar.getInstance();
        EditText editTextDate = (EditText) popUpView.findViewById(R.id.edit_text_date_set_date);
        EditText editTextName = (EditText) popUpView.findViewById(R.id.edit_text_date_set_title);
        EditText editTextDescription = (EditText) popUpView.findViewById(R.id.edit_text_date_set_description);
        EditText editTextCalories = (EditText) popUpView.findViewById(R.id.edit_text_date_set_kalorien);
        EditText editTextWeight = (EditText) popUpView.findViewById(R.id.edit_text_date_set_gewicht);

        //DatePickerDialog Setup und OnDataSet
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat formatterCutTime = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
                SimpleDateFormat formatterChangeFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    java.util.Date d = formatterCutTime.parse(c.getTime().toString());
                    editTextDate.setText(formatterChangeFormat.format(d));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };
        editTextDate.setOnClickListener(v -> {
            new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        //Create new DB Entry on Confirm Button
        confirmBtn.setOnClickListener(v -> {
            try {
                String dateString = editTextDate.getText().toString();
                String nameString = editTextName.getText().toString();
                String descriptionString = editTextDescription.getText().toString();
                String caloriesString = editTextCalories.getText().toString();
                String weightString = editTextWeight.getText().toString();
                java.util.Date choosenDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
                //Check for empty values
                if (nameString.isEmpty() || descriptionString.isEmpty() || caloriesString.isEmpty() || weightString.isEmpty()) {
                    popupWindow.dismiss();
                    Toast.makeText(getContext(), "Ein Wert wurde nicht gesetzt", Toast.LENGTH_SHORT).show();
                } else {
                    DataBaseConnector.getInstance().insert(new Date(choosenDate,
                            editTextName.getText().toString(),
                            editTextDescription.getText().toString(),
                            Integer.valueOf(editTextCalories.getText().toString()),
                            Integer.valueOf(editTextWeight.getText().toString())));
                    Toast.makeText(getContext(), "Neuer Eintrag wurde erstellts", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            } catch (ParseException e) {
                popupWindow.dismiss();
                Toast.makeText(getContext(), "Datum konnte nicht korrekt eingelesen Werden", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


        });
    }


    //Opens DeletePopUpWindow
    private void openDeletePopUpWindow(View view) {
        View popupView = getLayoutInflater().inflate(R.layout.fragment_delete_user, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(100);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button cancelbtn = popupView.findViewById(R.id.button_deleteuser_cancel);
        Button confirmbtn = popupView.findViewById(R.id.button_deleteuser_confirm);
        cancelbtn.setOnClickListener(v1 -> {
            popupWindow.dismiss();
        });
        confirmbtn.setOnClickListener(v1 -> {
            EditText userInput = (EditText) popupView.findViewById(R.id.edit_text_deleteuser_number);
            int id_to_be_deleted_date = Integer.valueOf(userInput.getText().toString());
            //TODO check if given UserId ist valid
            DataBaseConnector.getInstance().delete(new Date(id_to_be_deleted_date, null, "", "", 0, 0));
            Toast.makeText(getContext(), "Eintrag wurde gelöscht", Toast.LENGTH_LONG).show();
            popupWindow.dismiss();
        });
    }
}