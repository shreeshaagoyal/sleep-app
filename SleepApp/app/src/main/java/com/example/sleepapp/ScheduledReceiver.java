package com.example.sleepapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shreeshaagoyal on 5/8/2018.
 */

public class ScheduledReceiver extends BroadcastReceiver {

    private Calendar wakeUpCalendar;
    private Calendar fallAsleepCalendar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        this.wakeUpCalendar = Calendar.getInstance();
        this.fallAsleepCalendar = Calendar.getInstance();

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TAG");
        wl.acquire();
        Log.wtf("ScheduledReceiver", "In the ScheduledReceiver class! :D");
        Toast.makeText(context, "Hello buddies, we are in the ScheduledReceiver class", Toast.LENGTH_SHORT).show();
        wl.release();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setWakeUpMusic(Context context, int wakeUpHr, int wakeUpMin) {
        this.wakeUpCalendar.set(Calendar.HOUR_OF_DAY, wakeUpHr);
        this.wakeUpCalendar.set(Calendar.MINUTE, wakeUpMin);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ScheduledReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
    }
}
