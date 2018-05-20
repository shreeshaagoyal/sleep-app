package com.example.sleepapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int WAKE_UP_ACTIVITY = 0;
    private static final int FALL_ASLEEP_ACTIVITY = 1;

    private Button wakeUpButton;
    private Button fallAsleepButton;
    private int wakeUpHr;
    private int wakeUpMin;
    private int fallAsleepHr;
    private int fallAsleepMin;
    private int fallAsleepDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.wtf(Strings.MAIN, "hello boys. Main activity here");

        super.onCreate(savedInstanceState);
        this.setContentView(com.example.sleepapp.R.layout.activity_main);

        this.fallAsleepDuration = 5;

        setWakeUpButton();
        setFallAsleepButton();
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
        Intent wakeUpIntent = new Intent(this, WakeUpActivity.class);
        wakeUpIntent.putExtra(Strings.WAKE_UP_HR, this.wakeUpHr);
        wakeUpIntent.putExtra(Strings.WAKE_UP_MIN, this.wakeUpMin);
        wakeUpIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(wakeUpIntent, WAKE_UP_ACTIVITY);
    }

    private void startFallAsleepActivity() {
        Intent fallAsleepIntent = new Intent(this, FallAsleepActivity.class);
        fallAsleepIntent.putExtra(Strings.FALL_ASLEEP_HR, this.fallAsleepHr);
        fallAsleepIntent.putExtra(Strings.FALL_ASLEEP_MIN, this.fallAsleepMin);
        fallAsleepIntent.putExtra(Strings.FALL_ASLEEP_DURATION, this.fallAsleepDuration);
        fallAsleepIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(fallAsleepIntent, FALL_ASLEEP_ACTIVITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == WAKE_UP_ACTIVITY) && (resultCode == RESULT_OK)) {
            this.wakeUpHr = data.getIntExtra(Strings.WAKE_UP_HR, WAKE_UP_ACTIVITY);
            this.wakeUpMin = data.getIntExtra(Strings.WAKE_UP_MIN, WAKE_UP_ACTIVITY);
            Log.wtf(Strings.MAIN, "wake_up_hr: " + this.wakeUpHr);
            Log.wtf(Strings.MAIN, "wake_up_min: " + this.wakeUpMin);
        } else if ((requestCode == FALL_ASLEEP_ACTIVITY) && (resultCode == RESULT_OK)) {
            this.fallAsleepHr = data.getIntExtra(Strings.FALL_ASLEEP_HR, FALL_ASLEEP_ACTIVITY);
            this.fallAsleepMin = data.getIntExtra(Strings.FALL_ASLEEP_MIN, FALL_ASLEEP_ACTIVITY);
            this.fallAsleepDuration = data.getIntExtra(Strings.FALL_ASLEEP_DURATION, FALL_ASLEEP_ACTIVITY);
        }
    }
}
