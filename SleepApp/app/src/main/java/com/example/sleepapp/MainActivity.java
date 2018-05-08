package com.example.sleepapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int WAKE_UP_ACTIVITY = 0;
    private static final int FALL_ASLEEP_ACTIVITY = 1;

    private static final String FALL_ASLEEP_DURATION = "fall_asleep_duration";
    private static final String FALL_ASLEEP_HR = "fall_asleep_hr";
    private static final String FALL_ASLEEP_MIN = "fall_asleep_min";

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
        this.text = (TextView) findViewById(com.example.sleepapp.R.id.text);
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
        Intent intent = new Intent(this, WakeUpActivity.class);
        startActivityForResult(intent, WAKE_UP_ACTIVITY);
    }

    private void startFallAsleepActivity() {
        Intent intent = new Intent(this, FallAsleepActivity.class);
        intent.putExtra(FALL_ASLEEP_DURATION, this.fallAsleepDuration);
        intent.putExtra(FALL_ASLEEP_HR, this.fallAsleepHr);
        intent.putExtra(FALL_ASLEEP_MIN, this.fallAsleepMin);
        startActivityForResult(intent, FALL_ASLEEP_ACTIVITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == FALL_ASLEEP_ACTIVITY) && (resultCode == RESULT_OK)) {
            this.fallAsleepDuration = data.getIntExtra(FALL_ASLEEP_DURATION, FALL_ASLEEP_ACTIVITY);
            this.fallAsleepHr = data.getIntExtra(FALL_ASLEEP_HR, FALL_ASLEEP_ACTIVITY);
            this.fallAsleepMin = data.getIntExtra(FALL_ASLEEP_MIN, FALL_ASLEEP_ACTIVITY);
            this.text.setText("fall_asleep_duration: " + this.fallAsleepDuration + "\n" + "fall_asleep_hr: " + this.fallAsleepHr + "\n" + "fall_asleep_min: " + this.fallAsleepMin);
        }
    }
}
