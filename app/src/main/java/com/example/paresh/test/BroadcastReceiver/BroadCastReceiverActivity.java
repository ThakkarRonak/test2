package com.example.paresh.test.BroadcastReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.paresh.test.R;

public class BroadCastReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_receiver);

        }
    public void startAlert(View view)
    {
        EditText text =findViewById(R.id.tv_time);
        int i  = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent  =  PendingIntent.getBroadcast(this.getApplicationContext(),1,intent,0);

        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (i* 1000),pendingIntent);

        Toast.makeText(this, "Alaram is set in :-"+i+ " Secands", Toast.LENGTH_SHORT).show();

    }
}
