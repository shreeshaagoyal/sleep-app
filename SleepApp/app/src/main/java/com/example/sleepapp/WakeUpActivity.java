package com.example.sleepapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class WakeUpActivity extends SleepActivity {

    private int wakeUpHr;
    private int wakeUpMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        this.wakeUpHr = getIntent().getIntExtra(Strings.WAKE_UP_HR, 0);
        this.wakeUpMin = getIntent().getIntExtra(Strings.WAKE_UP_MIN, 0);

        Log.wtf(Strings.WAKE_UP, "this.wakeUpHr" + this.wakeUpHr + "\t" + "this.wakeUpMin" + this.wakeUpMin);

        setOkButton(WakeUpActivity.this);
        setSetTimeButton();
        setCancelButton();
        initializeTimeText(this.wakeUpHr, this.wakeUpMin);
    }

    @Override
    public void onBackPressed() {
        this.wakeUpHr = getHr();
        this.wakeUpMin = getMin();

        Intent intent = new Intent();
        intent.putExtra(Strings.WAKE_UP_HR, this.wakeUpHr);
        intent.putExtra(Strings.WAKE_UP_MIN, this.wakeUpMin);
        setResult(RESULT_OK, intent);

        finish();
    }
}
