package com.example.sleepapp;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

/**
 * Created by shreeshaagoyal on 5/3/2018.
 */

public class DurationPickerDialog extends TimePickerDialog {
    public DurationPickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
        updateTitle(hourOfDay, minute);
    }

    public DurationPickerDialog(Context context, int themeResId, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, themeResId, listener, hourOfDay, minute, is24HourView);
        updateTitle(hourOfDay, minute);
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        super.onTimeChanged(view, hourOfDay, minute);
        updateTitle(hourOfDay, minute);
    }

    private void updateTitle(int hourOfDay, int minute) {
        setTitle(getTimeOfValue(hourOfDay) + ":" + getTimeOfValue(minute));
    }

    private static String getTimeOfValue(int value) {
        if (value < 10) {
            return "0" + String.valueOf(value);
        } else {
            return String.valueOf(value);
        }
    }
}
