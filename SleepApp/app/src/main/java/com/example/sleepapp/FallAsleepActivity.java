package com.example.sleepapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class FallAsleepActivity extends SleepActivity implements AdapterView.OnItemSelectedListener {

    private static final String FALL_ASLEEP_DURATION = "fall_asleep_duration";

    private Spinner setTimeSpinner;
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_asleep);

        this.duration = getIntent().getIntExtra(FALL_ASLEEP_DURATION, 0);

        setSetTimeButton();
        setResetButton();
        initializeTimeText();
        setSetTimeSpinner();
    }

    private void initializeTimeText() {
        timeText = (TextView) findViewById(R.id.timeText);
        hr = 0;
        min = 0;
        updateTime(hr, min);
    }

    private void setSetTimeSpinner() {
        this.setTimeSpinner = (Spinner) findViewById(R.id.setTimeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.times));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setTimeSpinner.setAdapter(adapter);
        this.setTimeSpinner.setSelection((this.duration/5 )-1);
        this.setTimeSpinner.setOnItemSelectedListener(this);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(FALL_ASLEEP_DURATION, this.duration);
        setResult(RESULT_OK, intent);
        finish();
    }
}
