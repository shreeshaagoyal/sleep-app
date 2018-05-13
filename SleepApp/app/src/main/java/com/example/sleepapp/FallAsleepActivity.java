package com.example.sleepapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class FallAsleepActivity extends SleepActivity implements AdapterView.OnItemSelectedListener {

    private Spinner setTimeSpinner;
    private int fallAsleepHr;
    private int fallAsleepMin;
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_asleep);

        this.fallAsleepHr = getIntent().getIntExtra(Strings.FALL_ASLEEP_HR, 0);
        this.fallAsleepMin = getIntent().getIntExtra(Strings.FALL_ASLEEP_MIN, 0);
        this.duration = getIntent().getIntExtra(Strings.FALL_ASLEEP_DURATION, 0);

        hr = this.fallAsleepHr;
        min = this.fallAsleepMin;

        setOkButton();
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
        intent.putExtra(Strings.FALL_ASLEEP_HR, this.fallAsleepHr);
        intent.putExtra(Strings.FALL_ASLEEP_MIN, this.fallAsleepMin);
        intent.putExtra(Strings.FALL_ASLEEP_DURATION, this.duration);
        setResult(RESULT_OK, intent);

        Log.wtf(Strings.FALL_ASLEEP, "exiting FallAsleepActivity");

        finish();
    }
}
