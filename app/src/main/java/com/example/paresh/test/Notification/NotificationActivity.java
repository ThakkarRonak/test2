package com.example.paresh.test.Notification;

import android.app.*;
import android.app.Notification.DecoratedCustomViewStyle;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.paresh.test.BroadcastReceiver.MyBroadcastReceiver;
import com.example.paresh.test.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    Button button;
    Spinner spinner;
    TextView textView;

    String KEY_REPLY = "key_reply";
    public static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        button = findViewById(R.id.btn_notify2);
        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.txt_reply);
        clearExistingNotifications();

        final List<String> category = new ArrayList<String>();
        category.add("Simple Notificaton");
        category.add("Custom Notificaion");
        category.add("Notificaion With Chanel");
        category.add("Notificaion with Inline Reply");


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, category);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                switch (category.get(spinner.getSelectedItemPosition())) {

                    case "Simple Notificaton":
                        simpleNotifivation();

                        break;
                    case "Custom Notificaion":
                        customNotification();
                        break;
                    case "Notificaion With Chanel":
                        notificaionWithChanel();
                        break;
                    case "Notificaion with Inline Reply":
                        notificaionWithInlinereply();
                        break;
                }
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notificaionWithInlinereply() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentText("Inline Reply")
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_launcher_foreground);

        String replyLabel = "Enter Reply";

           RemoteInput remoteInput = new RemoteInput.Builder(KEY_REPLY)
                .setLabel(replyLabel)
                .build();

                Intent resultIntent = new Intent(this, MyBroadcastReceiver.class);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent resultPendingIntent = PendingIntent.getBroadcast(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground, "Reply", resultPendingIntent)
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build();
        builder.addAction(replyAction);

        String KEY_REPLY = "key_reply";

/*

        RemoteInput[] inputs = new RemoteInput[]{new RemoteInput.Builder(KEY_REPLY).setLabel("key_reply").build()};
        Intent intent = new Intent(ACTION_REMOTE_INPUT);
        intent.putExtra(EXTRA_REMOTE_INPUTS, inputs);
        startActivity(intent);
*/
        Intent intent1 = new Intent(this, NotificationActivity.class);
        intent1.putExtra("notificationId", NOTIFICATION_ID);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent dismissIntent = PendingIntent.getActivity(getBaseContext(), 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "DISMISS", dismissIntent);


        Bundle result = RemoteInput.getResultsFromIntent(resultIntent);
           if (result != null) {
               CharSequence charSequence = result.getCharSequence(KEY_REPLY);
               textView.setText(charSequence);
           }
         NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
         notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


    private void clearExistingNotifications() {
        int notificationId = getIntent().getIntExtra("notificationId", 0);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationId);
    }


    private void customNotification() {

        RemoteViews notifiylayout = new RemoteViews(getPackageName(), R.layout.noification_layout);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setStyle(new DecoratedCustomViewStyle())
                    .setCustomBigContentView(notifiylayout)
                    .setAutoCancel(true)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify((int) System.currentTimeMillis(), notification);

        }
    }

    private void notificaionWithChanel() {

        Intent intent = new Intent(NotificationActivity.this, NotificationChanelActivity.class);
        startActivity(intent);

    }

    private void simpleNotifivation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String CHANEL_ID = "chanel_id";
            String CHANEL_NAME = "chanel_name";

            NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setShowBadge(true);


            Notification notification = new Notification.Builder(this)


                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("Hello")
                    .setColor(getResources().getColor(R.color.blue))
                    .setPriority(Notification.PRIORITY_HIGH)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify((int) System.currentTimeMillis(), notification);
        }
    }
}
