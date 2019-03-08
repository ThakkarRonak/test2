package com.example.paresh.test.AlramManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.paresh.test.R;

import java.util.Calendar;

public class AlaramManagerActivity extends AppCompatActivity {
Button start;
TimePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alaram_manager);



        picker = findViewById(R.id.tp_time);
        start = findViewById(R.id.btn_click);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar =Calendar.getInstance();
                startAlaram(calendar.getTimeInMillis());
            }
        });

    }

    private void startAlaram(Long time) {

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(this,MyBroadcastReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,i,0);

        alarmManager.setRepeating(AlarmManager.RTC,time,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this, "Alram is set", Toast.LENGTH_SHORT).show();

    }


}
