package com.example.sleepapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button wakeUpButton;
    private Button fallAsleepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(com.example.sleepapp.R.layout.activity_main);
        setWakeUpButton();
        setFallAsleepButton();
    }

    /** PRIVATE METHODS */
    private void setWakeUpButton() {
        this.wakeUpButton = (Button) this.findViewById(com.example.sleepapp.R.id.wake_up);
        this.wakeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWakeUpActivity();
            }
        });
    }

    private void setFallAsleepButton() {
        this.fallAsleepButton = (Button) this.findViewById(com.example.sleepapp.R.id.fall_asleep);
        this.fallAsleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFallAsleepActivity();
            }
        });
    }

    private void startWakeUpActivity() {
        Intent intent = new Intent(this, WakeUpActivity.class);
        startActivity(intent);
    }

    private void startFallAsleepActivity() {
        Intent intent = new Intent(this, FallAsleepActivity.class);
        startActivity(intent);
    }
}
