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

    private Spinner setTimeSpinner;
    private int duration = 5;

    private TextView tempText; // TODO: DELETE THIS VARIABLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_asleep);
        setSetTimeButton();
        setResetButton();
        initializeTimeText();
        setSetTimeSpinner();

        this.tempText = (TextView) findViewById(R.id.tempText);
        this.tempText.setText("tempText");
        Toast.makeText(this, "FallAsleepActivity", Toast.LENGTH_SHORT).show();
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
        this.tempText.setText("duration: " + this.duration);
        Toast.makeText(this, "duration: " + this.duration, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No implementation needed
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("fall_asleep_duration", this.duration);
        setResult(RESULT_OK, intent);
        finish();
    }
}
