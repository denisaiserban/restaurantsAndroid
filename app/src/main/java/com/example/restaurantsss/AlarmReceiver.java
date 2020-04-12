package com.example.restaurantsss;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    private NotificationHelper mNotificationHelper;
    private String message;
    private String title;

    @Override
    public void onReceive(Context context, Intent intent) {
        getMessageAndTitle(intent);

        mNotificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = mNotificationHelper.getChannelNotification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build());

    }

    private void getMessageAndTitle(Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras == null) {
            message = null;
            title = null;
        } else {
            message = extras.getString("message");
            title = extras.getString("title");
        }
    }

}
