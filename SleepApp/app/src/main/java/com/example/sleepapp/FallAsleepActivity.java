package com.example.sleepapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class FallAsleepActivity extends SleepActivity implements AdapterView.OnItemSelectedListener {

    private static final String FALL_ASLEEP_DURATION = "fall_asleep_duration";
    private static final String FALL_ASLEEP_HR = "fall_asleep_hr";
    private static final String FALL_ASLEEP_MIN = "fall_asleep_min";

    private Spinner setTimeSpinner;
    private int duration;
    private int fallAsleepHr;
    private int fallAsleepMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_asleep);

        this.fallAsleepHr = getIntent().getIntExtra(FALL_ASLEEP_HR, 0);
        this.fallAsleepMin = getIntent().getIntExtra(FALL_ASLEEP_MIN, 0);
        this.duration = getIntent().getIntExtra(FALL_ASLEEP_DURATION, 0);

        hr = this.fallAsleepHr;
        min = this.fallAsleepMin;

        setSetTimeButton();
        setResetButton();
        initializeTimeText(this.fallAsleepHr, this.fallAsleepMin);
        setSetTimeSpinner();
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
        this.fallAsleepHr = getHr();
        this.fallAsleepMin = getMin();

        Intent intent = new Intent();
        intent.putExtra(FALL_ASLEEP_HR, this.fallAsleepHr);
        intent.putExtra(FALL_ASLEEP_MIN, this.fallAsleepMin);
        intent.putExtra(FALL_ASLEEP_DURATION, this.duration);
        setResult(RESULT_OK, intent);
        finish();
    }
}
