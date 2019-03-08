package com.example.paresh.test.AlramManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        MediaPlayer mediaPlayer= MediaPlayer.create(context,Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.start();

        Log.d("myalram","Alram just fired");
    }
}
