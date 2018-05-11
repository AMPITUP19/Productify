package com.example.nag.productify;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Manages the notifications which are sent to the user reminding them to begin working.
 */
public class NotificationReceiver extends BroadcastReceiver {
    @Override
    /**
     * Sends the user a notification
     */
    public void onReceive(Context context, Intent intent) {

        /** NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

         Intent repeating_intent = new Intent(context, Repeat.class);

         repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

         PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

         NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
         .setContentIntent(pendingIntent)
         .setContentTitle("Medicine Reminder")
         .setContentText("It is time for you to take your medicine!")
         .setAutoCancel(true);

         notificationManager.notify(100,builder.build()); */
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //if we want ring on notifcation then uncomment below line//
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                setSmallIcon(R.mipmap.ic_launcher_foreground).
                setContentIntent(pendingIntent).
                setContentText("You have a task to complete").
                setContentTitle("Productify Notification").
//                setSound(alarmSound).
        setAutoCancel(true);
        notificationManager.notify(100,builder.build());

    }
}


