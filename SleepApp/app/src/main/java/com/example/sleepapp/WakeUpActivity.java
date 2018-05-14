package com.example.sleepapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WakeUpActivity extends SleepActivity {

    private int wakeUpHr;
    private int wakeUpMin;

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    protected Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        this.wakeUpHr = getIntent().getIntExtra(Strings.WAKE_UP_HR, 0);
        this.wakeUpMin = getIntent().getIntExtra(Strings.WAKE_UP_MIN, 0);

        this.alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        setOkButton();
        setSetTimeButton();
        setResetButton();
        initializeTimeText(this.wakeUpHr, this.wakeUpMin);
    }

    @Override
    protected void setOkButton() {
        this.okButton = (Button) this.findViewById(R.id.ok);
        this.okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onOkClicked();
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.wakeUpHr = getHr();
        this.wakeUpMin = getMin();

        Intent intent = new Intent();
        intent.putExtra(Strings.WAKE_UP_HR, this.wakeUpHr);
        intent.putExtra(Strings.WAKE_UP_MIN, this.wakeUpMin);
        setResult(RESULT_OK, intent);

        finish();
    }

    protected void onOkClicked() {
        updateTime();

        Log.wtf(Strings.WAKE_UP, "ALARM ON");
        Toast.makeText(WakeUpActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.HOUR_OF_DAY, this.wakeUpHr);
        calendar.set(java.util.Calendar.MINUTE, this.wakeUpMin);

        Log.wtf("TAG", "hour: " + this.wakeUpHr + "\t" + "min: " + this.wakeUpMin);

        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10000, pendingIntent);
    }

    /** PRIVATE METHOD(S) */
    private void updateTime() {
        this.wakeUpHr = getHr();
        this.wakeUpMin = getMin();
    }
}
