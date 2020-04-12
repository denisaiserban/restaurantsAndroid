package com.example.restaurantsss;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.restaurantsss.picker.DatePickerFragment;
import com.example.restaurantsss.picker.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class ReservationFragment extends Fragment {

    private static TextView timeTextView;
    private static TextView dateTextView;
    private Button timePickerButton;
    private Button datePickerButton;
    private Button notificationButton;
    private String title;
    private static Calendar timeCalendar = Calendar.getInstance();
    public static final int REQUEST_CODE = 101;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reservation_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
        initTimePickerButton();
        initDatePickerButton();
        initNotificationButton();
    }

    private void initViews() {
        timeTextView = getView().findViewById(R.id.time_textview);
        dateTextView = getView().findViewById(R.id.date_text_view);
    }

    private void initDatePickerButton() {
        datePickerButton = getView().findViewById(R.id.date_picker_button);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                if (getFragmentManager() != null) {
                    datePicker.show(getFragmentManager(), "Alege data rezervarii");
                }
            }
        });
    }

    private void initTimePickerButton() {
        timePickerButton = getView().findViewById(R.id.time_picker_button);

        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                if (getFragmentManager() != null) {
                    timePicker.show(getFragmentManager(), "Alege ora reservarii");
                }
            }
        });
    }
    private void initNotificationButton(){
        notificationButton = getView().findViewById(R.id.reservation_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel(title,"Aveti o rezervare", timeCalendar);
            }
        });
    }

    public void sendOnChannel(String title, String message, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        intent.putExtra("title",title);
        intent.putExtra("message",message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(getContext(), "Alarm successfully created.", Toast.LENGTH_SHORT).show();
    }

    public static void updateTimeTextView(Calendar c){
        String timeText = "Selected time: "+ DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        System.out.println(timeText);
        timeTextView.setText(timeText);
    }

    public static void updateDateTextView(Calendar c){
        String text = "Selected date: "+ DateFormat.getDateInstance().format(c.getTime());
        dateTextView.setText(text);
    }

    public static void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        timeCalendar.set(Calendar.MINUTE, minute);
        timeCalendar.set(Calendar.SECOND, 0);

        updateTimeTextView(calendar);
    }

    public static void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        timeCalendar.set(Calendar.YEAR, year);
        timeCalendar.set(Calendar.MONTH, month);
        timeCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        updateDateTextView(calendar);
    }


}
