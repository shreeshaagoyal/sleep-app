package com.example.sleepapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int WAKE_UP_ACTIVITY = 0;
    private final int FALL_ASLEEP_ACTIVITY = 1;

    private Button wakeUpButton;
    private Button fallAsleepButton;
    private int wakeUpHr;
    private int wakeUpMin;
    private int fallAsleepHr;
    private int fallAsleepMin;
    private int fallAsleepDuration;

    private TextView text; // TODO: DELETE THIS VARIABLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(com.example.sleepapp.R.layout.activity_main);
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
        Intent intent = new Intent(this, WakeUpActivity.class);
        startActivityForResult(intent, WAKE_UP_ACTIVITY);
    }

    private void startFallAsleepActivity() {
        Intent intent = new Intent(this, FallAsleepActivity.class);
        startActivityForResult(intent, FALL_ASLEEP_ACTIVITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WAKE_UP_ACTIVITY) {
            if (resultCode == RESULT_OK || resultCode == RESULT_CANCELED) {

            }
        } else if (requestCode == FALL_ASLEEP_ACTIVITY) {
            if (resultCode == RESULT_OK || resultCode == RESULT_CANCELED) {
                this.fallAsleepHr = data.getIntExtra("fall_asleep_hr", FALL_ASLEEP_ACTIVITY);
                this.fallAsleepMin = data.getIntExtra("fall_asleep_min", FALL_ASLEEP_ACTIVITY);
                this.fallAsleepDuration = data.getIntExtra("fall_asleep_duration", FALL_ASLEEP_ACTIVITY);
                this.text.setText(this.fallAsleepHr + ":" + this.fallAsleepMin + " " + this.fallAsleepDuration);
            }
        }
    }
}
