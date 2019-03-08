package com.example.paresh.test.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.paresh.test.R;

public class Main2Activity extends AppCompatActivity {

    Button click;
    TextView tv_show;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {

            String b = intent.getStringExtra("a");

            Toast.makeText(context, "Received " +b, Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        click =findViewById(R.id.btn_send);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter("BROADCAST_RANDOM_NUMBER"));

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i= 3;

                Intent intent= new Intent("BROADCAST_RANDOM_NUMBER");
                intent.putExtra("a","Local BroadcastReceiver");
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
//                Intent intent= new Intent(v.getContext(),broadcastReceiver);
             /*   PendingIntent pendingIntent =  PendingIntent.getBroadcast(v.getContext(),1,intent,0);

                AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (i* 1000),pendingIntent);*/
             }
        });
        }
        @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
