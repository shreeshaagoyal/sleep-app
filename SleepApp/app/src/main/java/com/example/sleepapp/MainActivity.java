package com.example.sleepapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int WAKE_UP_ACTIVITY = 0;
    private static final int FALL_ASLEEP_ACTIVITY = 1;

    private static final String WAKE_UP_HR = "wake_up_hr";
    private static final String WAKE_UP_MIN = "wake_up_min";
    private static final String FALL_ASLEEP_HR = "fall_asleep_hr";
    private static final String FALL_ASLEEP_MIN = "fall_asleep_min";
    private static final String FALL_ASLEEP_DURATION = "fall_asleep_duration";

    private Button wakeUpButton;
    private Button fallAsleepButton;
    private int wakeUpHr;
    private int wakeUpMin;
    private int fallAsleepHr;
    private int fallAsleepMin;
    private int fallAsleepDuration;

    private ScheduledReceiver alarmReceiver;

    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        this.setContentView(com.example.sleepapp.R.layout.activity_main);
        this.fallAsleepDuration = 5;
        setWakeUpButton();
        setFallAsleepButton();

        this.alarmReceiver = new ScheduledReceiver();
    }

    /** PRIVATE METHODS */
    private void setWakeUpButton() {
        this.wakeUpButton = (Button) this.findViewById(com.example.sleepapp.R.id.wake_up);
        this.wakeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWakeUpActivity();
            }
        });
    }

    private void setFallAsleepButton() {
        this.fallAsleepButton = (Button) this.findViewById(com.example.sleepapp.R.id.fall_asleep);
        this.fallAsleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFallAsleepActivity();
            }
        });
    }

    private void startWakeUpActivity() {
        Intent intent = new Intent(this, WakeUpActivity.class);
        intent.putExtra(WAKE_UP_HR, this.wakeUpHr);
        intent.putExtra(WAKE_UP_MIN, this.wakeUpMin);
        startActivityForResult(intent, WAKE_UP_ACTIVITY);
    }

    private void startFallAsleepActivity() {
        Intent intent = new Intent(this, FallAsleepActivity.class);
        intent.putExtra(FALL_ASLEEP_DURATION, this.fallAsleepDuration);
        intent.putExtra(FALL_ASLEEP_HR, this.fallAsleepHr);
        intent.putExtra(FALL_ASLEEP_MIN, this.fallAsleepMin);
        startActivityForResult(intent, FALL_ASLEEP_ACTIVITY);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == WAKE_UP_ACTIVITY) && (resultCode == RESULT_OK)) {
            this.wakeUpHr = data.getIntExtra(WAKE_UP_HR, WAKE_UP_ACTIVITY);
            this.wakeUpMin = data.getIntExtra(WAKE_UP_MIN, WAKE_UP_ACTIVITY);
            Log.wtf("j", "wake_up_hr: " + this.wakeUpHr);
            Log.wtf("j", "wake_up_min: " + this.wakeUpMin);
            setWakeUpMusic();
        } else if ((requestCode == FALL_ASLEEP_ACTIVITY) && (resultCode == RESULT_OK)) {
            this.fallAsleepDuration = data.getIntExtra(FALL_ASLEEP_DURATION, FALL_ASLEEP_ACTIVITY);
            this.fallAsleepHr = data.getIntExtra(FALL_ASLEEP_HR, FALL_ASLEEP_ACTIVITY);
            this.fallAsleepMin = data.getIntExtra(FALL_ASLEEP_MIN, FALL_ASLEEP_ACTIVITY);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setWakeUpMusic() {
        Intent intent = new Intent(this, ScheduledReceiver.class); // TODO: different than the tutorial

        if (this.alarmReceiver != null) {
            this.alarmReceiver.setWakeUpMusic(this.context, this.wakeUpHr, this.wakeUpMin);
        }
    }
}
