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
import android.widget.TextView;
import android.widget.Toast;

public class WakeUpActivity extends SleepActivity {

    private static final String WAKE_UP_HR = "wake_up_hr";
    private static final String WAKE_UP_MIN = "wake_up_min";

    private int wakeUpHr;
    private int wakeUpMin;

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    protected Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        this.wakeUpHr = getIntent().getIntExtra(WAKE_UP_HR, 0);
        this.wakeUpMin = getIntent().getIntExtra(WAKE_UP_MIN, 0);

        this.alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        setOkButton();
        setSetTimeButton();
        setResetButton();
        initializeTimeText(this.wakeUpHr, this.wakeUpMin);
    }

    @Override
    public void onBackPressed() {
        this.wakeUpHr = getHr();
        this.wakeUpMin = getMin();

        Intent intent = new Intent();
        intent.putExtra(WAKE_UP_HR, this.wakeUpHr);
        intent.putExtra(WAKE_UP_MIN, this.wakeUpMin);
        setResult(RESULT_OK, intent);

        // TODO: SET PENDING INTENT AND wakeUpCalendar

        finish();
    }

    protected void setOkButton() {
        this.okButton = (Button) this.findViewById(R.id.ok);
        this.okButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                onClickOkButton();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void onClickOkButton() {
        this.wakeUpHr = getHr();
        this.wakeUpMin = getMin();
        Toast.makeText(this.getApplicationContext(), "ALARM ON", Toast.LENGTH_SHORT).show();
        Log.wtf("TAG", "ALARM ON");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, this.wakeUpHr);
        calendar.set(Calendar.MINUTE, this.wakeUpMin);

        Toast.makeText(this.getApplicationContext(), "wake_up_hr: " + wakeUpHr + "\n" + "wake_up_min: " + wakeUpMin, Toast.LENGTH_LONG).show();
        Log.wtf("TAG", "wake_up_hr: " + wakeUpHr + "\n" + "wake_up_min: " + wakeUpMin);

        Intent intent = new Intent(this, ScheduledReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10000, pendingIntent);
    }
}
