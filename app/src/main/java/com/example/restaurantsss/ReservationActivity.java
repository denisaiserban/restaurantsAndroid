package com.example.restaurantsss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Objects;

public class ReservationActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{

    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Bundle extras = getIntent().getExtras();
        restaurantName = extras.getString("name");

        TextView descriptionTextView = findViewById(R.id.reservationTextView);
        descriptionTextView.setText("Rezerva acum la "+ restaurantName);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        ReservationFragment.onDateSet(view, year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        ReservationFragment.onTimeSet(view, hourOfDay, minute);
    }
}
