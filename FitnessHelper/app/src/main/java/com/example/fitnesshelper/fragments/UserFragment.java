package com.example.fitnesshelper.fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fitnesshelper.MainActivity;
import com.example.fitnesshelper.R;
import com.example.fitnesshelper.adapter.UserAdapter;
import com.example.fitnesshelper.db.DataBaseConnector;
import com.example.fitnesshelper.db.model.Date;
import com.example.fitnesshelper.db.model.User;
import com.google.android.material.internal.NavigationMenu;

import java.net.Inet4Address;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class UserFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).setActionBarTitle("Benutzerverwaltung");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        addFabSpeedDial(view);


        //Set Recycler View for User Profiles
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UserAdapter userAdapter =  new UserAdapter();
        DataBaseConnector.getInstance().getAllUsers().observe(requireActivity(), userAdapter::setUsers);
        recyclerView.setAdapter(userAdapter);
        return view;
    }

    //Adds an FabSpeedDial and their Actionlisteners
    private void addFabSpeedDial(View view) {
        FabSpeedDial fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.button_speed_dial_user);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add:
                        openAddUserPopUpWindow(view);
                        break;
                    case R.id.action_delete:
                        openDeleteUserPopUpWindow(view);
                        break;
                }
                return true;
            }
            @Override
            public void onMenuClosed() {

            }
        });
    }

    private void openAddUserPopUpWindow(View view) {
        View popUpView = getLayoutInflater().inflate(R.layout.fragment_create_user, null);
        PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(100);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Spinner spinner = popUpView.findViewById(R.id.spinner_createuser_sex);
        initSpinner(spinner);
        Button confirmButton = popUpView.findViewById(R.id.button_createuser_confirm);
        Button cancelButton = popUpView.findViewById(R.id.button_createuser_cancel);
        EditText editTextFirstName = popUpView.findViewById(R.id.edit_text_createuser_set_first_name);
        EditText editTextLastName = popUpView.findViewById(R.id.edit_text_createuser_set_last_name);
        EditText editTextWeight = popUpView.findViewById(R.id.edit_text_createuser_set_weight);
        EditText editTextHeight = popUpView.findViewById(R.id.edit_text_createuser_set_height);

        cancelButton.setOnClickListener(v -> popupWindow.dismiss());
        confirmButton.setOnClickListener(v -> {
            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String weight = editTextWeight.getText().toString();
            String height = editTextHeight.getText().toString();
            String sex = getSpinnerItemFromDropDown(spinner);
            if (firstName.isEmpty() || lastName.isEmpty() || weight.isEmpty() || height.isEmpty() || sex.isEmpty()){
                Toast.makeText(getContext(), "Alle Angaben müssen vollständig sein", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }else {
                DataBaseConnector.getInstance().insert(new User(firstName,lastName,Integer.valueOf(weight),Integer.valueOf(height),sex));
                popupWindow.dismiss();
            }
        });
    }

    private void initSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.genders, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0);
    }

    private String getSpinnerItemFromDropDown(Spinner spinner) {
        if (spinner.getSelectedItem().toString().equals("Geschlecht auswählen")){
            Toast.makeText(getContext(),"Bitte Geschlecht auswählen", Toast.LENGTH_SHORT).show();
        }else {
            return spinner.getSelectedItem().toString();
        }
        return "";
    }

    private void openDeleteUserPopUpWindow(View view) {
        View popUpView = getLayoutInflater().inflate(R.layout.fragment_delete_user, null);
        PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(100);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button confirmButton = popUpView.findViewById(R.id.button_deleteuser_confirm);
        Button cancelButton = popUpView.findViewById(R.id.button_deleteuser_cancel);
        cancelButton.setOnClickListener(v -> popupWindow.dismiss());
        confirmButton.setOnClickListener(v -> {
            EditText userInput = (EditText) popUpView.findViewById(R.id.edit_text_deleteuser_number);
            int id_to_be_deleted_user = Integer.valueOf(userInput.getText().toString());
            //TODO check if given UserId ist valid
            DataBaseConnector.getInstance().delete(new User(id_to_be_deleted_user,"","",0,0,""));
            Toast.makeText(getContext(), "Benutzer wurde gelöscht", Toast.LENGTH_LONG).show();
            popupWindow.dismiss();
        });
    }

}