package com.example.welcome.familyapp;

/**
 * Created by welcome on 7/30/2018.
 */

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Notification generated");
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("Birthday!");
            builder.setContentText("Today is personname's birthday");
            builder.setSmallIcon(R.drawable.best_app_icon);
            Intent notifyIntent = new Intent(context, EditorActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            //to be able to launch your activity from the notification
            builder.setContentIntent(pendingIntent);
            Notification notificationCompat = builder.build();
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
            managerCompat.notify(0, notificationCompat);
        }
    }

