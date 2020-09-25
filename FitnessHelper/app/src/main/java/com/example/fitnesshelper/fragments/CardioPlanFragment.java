package com.example.fitnesshelper.fragments;

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
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.fitnesshelper.R;
import com.example.fitnesshelper.adapter.CardioPlanAdapter;
import com.example.fitnesshelper.adapter.MusclePlanAdapter;
import com.example.fitnesshelper.db.DataBaseConnector;
import com.example.fitnesshelper.db.model.CardioPlan;
import com.example.fitnesshelper.db.model.MusclePlan;
import com.google.android.material.internal.NavigationMenu;

import java.util.Calendar;
import java.util.Date;

import javax.security.auth.callback.Callback;

import io.github.yavski.fabspeeddial.FabSpeedDial;


public class CardioPlanFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cardio_plan, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_cardio_plan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CardioPlanAdapter cardioPlanAdapter = new CardioPlanAdapter(getContext());
        DataBaseConnector.getInstance().getAllCardioPlans().observe(requireActivity(), cardioPlanAdapter::setCardioPlans);
        recyclerView.setAdapter(cardioPlanAdapter);
        addFabSpeedDial(view);
        return view;
    }

    private void addFabSpeedDial(View view) {
        FabSpeedDial fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.button_speed_dial_cardio_plan);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add:
                        Toast.makeText(getContext(), "Hier könnte ein neuer Individueller Plan erstellt werden", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_delete:
                        openDeletePopUpWindow(view);
                        break;
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }

    //Opens DeletePopUpWindow
    private void openDeletePopUpWindow(View view) {
        View popupView = getLayoutInflater().inflate(R.layout.fragment_delete_plan, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(100);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button cancelbtn = popupView.findViewById(R.id.button_deleteplan_cancel);
        Button confirmbtn = popupView.findViewById(R.id.button_deleteplan_confirm);
        cancelbtn.setOnClickListener(v1 -> {
            popupWindow.dismiss();
        });
        confirmbtn.setOnClickListener(v1 -> {
            EditText userInput = (EditText) popupView.findViewById(R.id.edit_text_deleteplan_number);
            int id_to_be_deleted_plan = Integer.valueOf(userInput.getText().toString());
            //TODO check if given UserId ist valid
            DataBaseConnector.getInstance().delete(new CardioPlan(id_to_be_deleted_plan, "", "", new Date()));
            Toast.makeText(getContext(), "Plan wurde gelöscht", Toast.LENGTH_LONG).show();
            popupWindow.dismiss();
        });
    }
}