package com.example.sleepapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shreeshaagoyal on 5/8/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getExtras().getString(Strings.STATE);
        Intent playRingtone = new Intent(context, RingtonePlayingService.class);
        playRingtone.putExtra(Strings.STATE, state);
        context.startService(playRingtone);

        Log.wtf("TAG", "STATE: " + state);
    }
}
