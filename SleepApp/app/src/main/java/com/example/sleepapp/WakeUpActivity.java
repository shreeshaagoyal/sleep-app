package com.example.sleepapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class WakeUpActivity extends SleepActivity {

    private static final String WAKE_UP_HR = "wake_up_hr";
    private static final String WAKE_UP_MIN = "wake_up_min";

    private int wakeUpHr;
    private int wakeUpMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        this.wakeUpHr = getIntent().getIntExtra(WAKE_UP_HR, 0);
        this.wakeUpMin = getIntent().getIntExtra(WAKE_UP_MIN, 0);

        setOkButton();
        setSetTimeButton();
        setResetButton();
        initializeTimeText(this.wakeUpHr, this.wakeUpMin);
    }

    @Override
    public void onBackPressed() {
        this.wakeUpHr = getHr();
        this.wakeUpMin = getMin();

        Intent intent = new Intent();
        intent.putExtra(WAKE_UP_HR, this.wakeUpHr);
        intent.putExtra(WAKE_UP_MIN, this.wakeUpMin);
        setResult(RESULT_OK, intent);

        // TODO: SET PENDING INTENT AND wakeUpCalendar

        finish();
    }
}
