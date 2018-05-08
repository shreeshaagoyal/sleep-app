package com.example.sleepapp;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public abstract class SleepActivity extends AppCompatActivity {

    protected Button setTimeButton;
    protected Button resetButton;
    protected int hr;
    protected int min;
    protected TextView timeText;

    protected void setSetTimeButton() {
        this.setTimeButton = (Button) this.findViewById(R.id.setTimeButton);
        this.setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();
                hr = currentTime.get(Calendar.HOUR_OF_DAY);
                min = currentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(SleepActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        updateTime(selectedHour, selectedMinute);
                    }
                }, hr, min, false);
                timePickerDialog.show();
            }
        });
    }

    protected static String getTimeOfValue(int value) {
        if (value < 10) {
            return "0" + String.valueOf(value);
        } else {
            return String.valueOf(value);
        }
    }

    protected void updateTime(int hours, int minutes) {
        this.timeText.setText(getTimeToString(hours, minutes));
        hr = hours;
        min = minutes;
    }

    protected void setResetButton() {
        this.resetButton = (Button) this.findViewById(R.id.resetButton);
        this.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hr = 0;
                min = 0;
                updateTime(hr, min);
            }
        });
    }

    protected String getTimeToString(int hr, int min) {
        String period;
        if (hr > 12) {
            hr -= 12;
            period = "PM";
        } else if (hr == 12) {
            period = "PM";
        } else if (hr == 0) {
            hr = 12;
            period = "AM";
        } else {
            period = "AM";

        }
        StringBuffer result = new StringBuffer(getTimeOfValue(hr) + ":" + getTimeOfValue(min) + " " + period);
        return result.toString();
    }

    protected void initializeTimeText(int hours, int minutes) {
        timeText = (TextView) findViewById(R.id.timeText);
        hr = hours;
        min = minutes;
        updateTime(hr, min);
    }

    protected int getHr() {
        return this.hr;
    }

    protected int getMin() {
        return this.min;
    }
}
