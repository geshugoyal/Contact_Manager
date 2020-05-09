package com.example.welcome.familyapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by welcome on 7/30/2018.
 */

public class DayNotification extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DayNotification(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    public void notiMethod(int day, int month) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date date = new Date();
        Date d1 = formatter.parse(String.valueOf(date));
        Calendar calender= Calendar.getInstance();
        calender.setTime(d1);
        int m= calender.get(calender.MONTH);
        int d= calender.get(calender.DAY_OF_MONTH);

       // for(int i=1; i<=12; i++){
           // for(int j=1;j<=31;j++){
                /*if(m==month && d==day){

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                            .setContentTitle("Birthday notification")
                            .setContentText("Today is Birthday")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE);
                    System.out.println("day:"+ day+"month:"+month);



                    @Override
                    protected void onHandleIntent(Intent intent) {
                */        Notification.Builder builder = new Notification.Builder(this);
                        builder.setContentTitle("My Title");
                        builder.setContentText("This is the Body");
                        builder.setSmallIcon(R.drawable.best_app_icon);
                        Intent notifyIntent = new Intent(this, MainActivity.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        //to be able to launch your activity from the notification
                        builder.setContentIntent(pendingIntent);
                        Notification notificationCompat = builder.build();
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
                        managerCompat.notify(0, notificationCompat);
                  /*  }
*/
                }
           // }
       // }
    }

