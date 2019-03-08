package com.example.paresh.test.BoundService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.paresh.test.R;

public class BoundServiceActivity extends AppCompatActivity {
    Bound bound;
    boolean servicebound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);

        final TextView Time = findViewById(R.id.tv_time);
        final Button printTime = findViewById(R.id.btn_start);
        Button stopTime = findViewById(R.id.btn_stop);

        printTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (servicebound) {
                    Time.setText(bound.gettime());
                }
            }
        });


        stopTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (servicebound) {
                    unbindService(connection);
                    servicebound = false;
                }
                Intent intent = new Intent(BoundServiceActivity.this, Bound.class);
                stopService(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, Bound.class);
        startService(intent);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (servicebound) {
            unbindService(connection);
            servicebound = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Bound.MyBinder myBinder = (Bound.MyBinder) service;
            bound = myBinder.getBound();
            servicebound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            servicebound = false;
        }
    };
}
