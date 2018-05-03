package com.example.sleepapp;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public abstract class SleepActivity extends AppCompatActivity {

    private Button setTimeButton;
    private Button resetButton;
    private int hr;
    private int min;
    private TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);
        setSetTimeButton();
        setResetButton();
        this.timeText = (TextView) findViewById(R.id.timeText);
        this.hr = 0;
        this.min = 0;
        updateTime(this.hr, this.min);
    }

    private void setSetTimeButton() {
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

    private static String getTimeOfValue(int value) {
        if (value < 10) {
            return "0" + String.valueOf(value);
        } else {
            return String.valueOf(value);
        }
    }

    private void updateTime(int hours, int minutes) {
        this.timeText.setText(getTimeToString(hours, minutes));
    }

    private void setResetButton() {
        this.resetButton = (Button) this.findViewById(R.id.resetButton);
        this.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hr = 0;
                min = 0;
                timeText.setText(getTimeToString(hr, min));
            }
        });
    }

    private String getTimeToString(int hr, int min) {
        String period;
        if (hr > 12) {
            hr -= 12;
            period = "PM";
        } else {
            period = "AM";
        }

        StringBuffer result = new StringBuffer(getTimeOfValue(hr) + ":" + getTimeOfValue(min) + " " + period);
        return result.toString();
    }
}
