package com.example.sleepapp;

import android.os.Bundle;
import android.widget.TextView;

public class WakeUpActivity extends SleepActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);
        setSetTimeButton();
        setResetButton();
        timeText = (TextView) findViewById(R.id.timeText);
        hr = 0;
        min = 0;
        updateTime(hr, min);
    }
}
