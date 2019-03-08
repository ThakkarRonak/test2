package com.example.paresh.test.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.paresh.test.BroadcastReceiver.MyBroadcastReceiver;
import com.example.paresh.test.R;

import java.util.Random;

public class NotificationWithReplyActivity extends AppCompatActivity implements View.OnClickListener {

    String KEY_REPLY = "key_reply";
    public static final int NOTIFICATION_ID = 1;

    Button notify;
    TextView tv_reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_with_reply);

        tv_reply = findViewById(R.id.tv_reply);
        notify = findViewById(R.id.btn_notificaion);
        notify.setOnClickListener(this);
        clearExistingNotifications();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        Toast.makeText(this, "App Closed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "App Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        proceessinlineReply(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_notificaion:

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_notify_chat)
                        .setContentTitle("Inline Reply Notification");


                String replyLabel = "Enter your reply here";

                RemoteInput remoteInput = new RemoteInput.Builder(KEY_REPLY)
                        .setLabel(replyLabel)
                        .build();


                int randomRequestCode = new Random().nextInt(54325);

                Intent resultIntent = new Intent(this, NotificationWithReplyActivity.class);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(this, randomRequestCode, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                        android.R.drawable.sym_action_chat, "REPLY", resultPendingIntent)
                        .addRemoteInput(remoteInput)
                        .setAllowGeneratedReplies(true)
                        .build();
                builder.addAction(replyAction);
//                 builder.setAutoCancel(true);

                Intent intent = new Intent(this, MyBroadcastReceiver.class);
                intent.putExtra("notificationId", NOTIFICATION_ID);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent dismissIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "DISMISS", dismissIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(NOTIFICATION_ID, builder.build());
                break;
               /* String CHANEL_ID ="chanel_id";
                CharSequence CHANEL_NAME ="MY_CHANEL";*/

               /* NotificationChannel channel = new NotificationChannel(CHANEL_ID,CHANEL_NAME,NotificationManager.IMPORTANCE_HIGH);
                channel.setShowBadge(true);
                notificationManager.createNotificationChannel(channel);

                notificationManager.notify(NOTIFICATION_ID,
                        builder.build());*/

        }

    }

    private void clearExistingNotifications() {
        int notificationId = getIntent().getIntExtra("notificationId", 0);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationId);
    }

    private void proceessinlineReply(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput != null) {
            String reply = remoteInput.getCharSequence(KEY_REPLY).toString();


            tv_reply.setText(reply);

            NotificationCompat.Builder repliedNotification =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.stat_notify_chat)
                            .setContentText("Inline Reply received");

            NotificationManager notificationManager =
                    (NotificationManager)
                            getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID,
                    repliedNotification.build());
        }
    }
    /*private CharSequence getReplymsg(Intent intent){

        Bundle remoteinput = RemoteInput.getResultsFromIntent(intent);
        if (remoteinput != null)
        {
            return remoteinput.getCharSequence(KEY_REPLY);

        }
        return null;
    }*/
}
