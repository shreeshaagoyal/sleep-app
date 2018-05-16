package com.example.sleepapp;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

public class RingtonePlayingService extends Service {
    private Ringtone ringtone;
    private boolean isMusicPlaying;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.wtf("TAG", "In the onStartCommand() method in RingtonePlayingService class!");

        // TODO: NOTIFY THAT THE ALARM IS GOING OFF

        // TODO: CHANGE TYPE OF startId FROM INT TO ENUM

        String state = intent.getExtras().getString(Strings.STATE);
        startId = updateStartId(state);

        if (!this.isMusicPlaying && startId == 1) { // no ringtone playing, want to start ringtone
            playRingtone();
            this.isMusicPlaying = true;
        } else if (!this.isMusicPlaying && startId == 0) { // no ringtone playing, want to stop ringtone
            this.isMusicPlaying = false; // TODO: SEE IF THIS LOC IS NEEDED
            // no other implementation
        } else if (this.isMusicPlaying && startId == 1) { // ringtone playing, want to start ringtone
            this.isMusicPlaying = true;
        } else { // ringtone playing, want to stop ringtone
            stopRingtone();
            this.isMusicPlaying = false;
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        this.ringtone.stop();
    }

    /** PRIVATE METHOD(S) */
    private void playRingtone() {
        Log.wtf("TAG", "playing ringtone...");

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        this.ringtone = RingtoneManager.getRingtone(this, alarmUri);
        this.ringtone.play();
    }

    private void stopRingtone() {
        Log.wtf("TAG", "stopping ringtone...");

        this.ringtone.stop();
    }

    private int updateStartId(String state) {
        if (state.equals(Strings.WANT_TO_STOP)) {
            return 0;
        } else if (state.equals(Strings.WANT_TO_START)) {
            return 1;
        } else {
            throw new IllegalArgumentException("state " + state + "not valid");
        }
    }
}
