package com.example.sleepapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public abstract class SleepActivity extends AppCompatActivity {

    protected Button setTimeButton;
    protected Button cancelButton;
    protected Button okButton;

    protected int hr;
    protected int min;

    protected TextView timeText;

    protected PendingIntent pendingIntent;
    protected AlarmManager alarmManager;
    protected Intent intent;
    protected Context context;

    protected void setOkButton(final Context context) {
        this.context = context;
        this.okButton = (Button) this.findViewById(R.id.ok);
        this.okButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                onOkClicked(context);
            }
        });
    }

    protected void setSetTimeButton() {
        this.setTimeButton = (Button) this.findViewById(R.id.setTimeButton);
        this.setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();
                hr = currentTime.get(Calendar.HOUR_OF_DAY);
                min = currentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(SleepActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        updateTime(selectedHour, selectedMinute);
                    }
                }, hr, min, false);
                timePickerDialog.show();
            }
        });
    }

    protected void setCancelButton() {
        this.cancelButton = (Button) this.findViewById(R.id.cancelButton);
        this.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent != null) {
                    intent.putExtra(Strings.STATE, Strings.WANT_TO_STOP);
                    sendBroadcast(intent);
                    if (alarmManager != null) {
                        alarmManager.cancel(pendingIntent);
                    }
                }
                Log.wtf(Strings.SLEEP, "ALARM OFF");
                Toast.makeText(context, "ALARM OFF", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initializeTimeText(int hours, int minutes) {
        timeText = (TextView) findViewById(R.id.timeText);
        hr = hours;
        min = minutes;
        updateTime(hr, min);
    }

    protected int getHr() {
        return this.hr;
    }

    protected int getMin() {
        return this.min;
    }

    /** PRIVATE METHODS */
    private void updateTime(int hours, int minutes) {
        this.timeText.setText(TimeUtilTools.getTimeToString(hours, minutes));
        this.hr = hours;
        this.min = minutes;
    }

    private void updateTime() {
        this.hr = this.getHr();
        this.min = this.getMin();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void onOkClicked(Context context) {
        updateTime();
        this.alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Log.wtf(Strings.WAKE_UP, "ALARM ON");
        Toast.makeText(this, "ALARM ON", Toast.LENGTH_SHORT).show();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.HOUR_OF_DAY, this.hr);
        calendar.set(java.util.Calendar.MINUTE, this.min);

        Log.wtf(Strings.SLEEP, "hour: " + this.hr + "\t" + "min: " + this.min);

        this.intent = new Intent(context, AlarmReceiver.class);
        this.intent.putExtra(Strings.STATE, Strings.WANT_TO_START);
        this.pendingIntent = PendingIntent.getBroadcast(context, 0, this.intent, 0);
        this.alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
