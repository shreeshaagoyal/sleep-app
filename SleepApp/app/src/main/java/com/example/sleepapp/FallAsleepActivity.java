package com.example.sleepapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


public class FallAsleepActivity extends SleepActivity {

    private Spinner setTimeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_asleep);
        setSetTimeButton();
        setResetButton();
        timeText = (TextView) findViewById(R.id.timeText);
        hr = 0;
        min = 0;
        updateTime(hr, min);
        setSetTimeSpinner();
    }

    private void setSetTimeSpinner() {
        this.setTimeSpinner = (Spinner) findViewById(R.id.setTimeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.times));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setTimeSpinner.setAdapter(adapter);
    }
}
