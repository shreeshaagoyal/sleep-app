package com.example.sleepapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class FallAsleepActivity extends SleepActivity implements AdapterView.OnItemSelectedListener {

    private Spinner setTimeSpinner;
    private int duration;

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (pos) {
            case 0:
                this.duration = 5;
                break;
            case 1:
                this.duration = 10;
                break;
            case 2:
                this.duration = 15;
                break;
            case 3:
                this.duration = 20;
                break;
            case 4:
                this.duration = 25;
                break;
            case 5:
                this.duration = 30;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No implementation needed
    }
}
