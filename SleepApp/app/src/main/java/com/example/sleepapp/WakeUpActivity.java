package com.example.sleepapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WakeUpActivity extends SleepActivity {

    private int wakeUpHr;
    private int wakeUpMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        this.wakeUpHr = getIntent().getIntExtra(Strings.WAKE_UP_HR, 0);
        this.wakeUpMin = getIntent().getIntExtra(Strings.WAKE_UP_MIN, 0);

        setOkButton(WakeUpActivity.this, this.wakeUpHr, this.wakeUpMin);
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

    @Override
    protected void setCancelButton() {
        this.cancelButton = (Button) this.findViewById(R.id.cancelButton);
        this.cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (alarmManager != null) {
                    alarmManager.cancel(pendingIntent);
                }
                Log.wtf(Strings.WAKE_UP, "ALARM OFF");
                Toast.makeText(WakeUpActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
