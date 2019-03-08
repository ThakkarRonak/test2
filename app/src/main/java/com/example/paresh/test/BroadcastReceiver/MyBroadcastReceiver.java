package com.example.paresh.test.BroadcastReceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import com.example.paresh.test.R;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        String KEY_REPLY = "key_reply";
//        Toast.makeText(context, "Your Time Is Up", Toast.LENGTH_LONG).show();

      String result=  intent.getStringExtra(KEY_REPLY);

        Toast.makeText(context, "Your Reply is "+ result, Toast.LENGTH_SHORT).show();

//        getNotification(context);
    }



    private void getNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder .setSmallIcon(R.drawable.android_icon)
                .setContentTitle("Notify")
                .setContentText("data Send Successfully");

        NotificationManager notificationManager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
        }
}
