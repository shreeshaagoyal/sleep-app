package com.example.sleepapp;

public class TimeUtilTools {
    public static String getTimeToString(int hr, int min) {
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

    private static String getTimeOfValue(int value) {
        if (value < 10) {
            return "0" + String.valueOf(value);
        } else {
            return String.valueOf(value);
        }
    }

    public static int getDurationFromPos(int pos) {
        switch (pos) {
            case 0:
                return 5;
            case 1:
                return 10;
            case 2:
                return 15;
            case 3:
                return 20;
            case 4:
                return 25;
            case 5:
                return 30;
        }
        throw new IllegalArgumentException("Invalid position " + pos);
    }
}
