package com.example.sleepapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


public class FallAsleepActivity extends SleepActivity {

    private Button setDurationButton;
    private TextView durationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_asleep);
        setSetTimeButton();
        setResetButton();
        timeText = (TextView) findViewById(R.id.timeText);
        this.durationText = (TextView) findViewById(R.id.durationText);
        hr = 0;
        min = 0;
        updateTime(hr, min);
        setSetDurationButton();
    }

    private void setSetDurationButton() {
        this.setDurationButton = (Button) findViewById(R.id.durationButton);
        this.setDurationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DurationPickerDialog durationPickerDialog = new DurationPickerDialog(FallAsleepActivity.this, new DurationPickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        updateTimerTime(hour, minute);
                    }
                }, hr, min, false);
                durationPickerDialog.show();
            }
        });
    }

    private void updateTimerTime(int hours, int minutes) {
        this.durationText.setText(getTimeToStringTimer(hours, minutes));
    }

    private String getTimeToStringTimer(int hour, int minute) {
        if (hour > 12) {
            hour -= 12;
        }

        StringBuffer result = new StringBuffer(getTimeOfValue(hour) + ":" + getTimeOfValue(minute));
        return result.toString();
    }
}
