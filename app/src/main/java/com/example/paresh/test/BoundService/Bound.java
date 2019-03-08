package com.example.paresh.test.BoundService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

public class Bound extends Service {
    private static String LOG_TAG ="BoundService";
    private IBinder iBinder = new MyBinder();
    private Chronometer mChronometer;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG,"in oncreate");
        mChronometer =new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.v(LOG_TAG,"in onBind");
        return iBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LOG_TAG,"in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG,"in onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG,"in onDestroy");
        mChronometer.stop();
    }
    public String gettime(){
        long time  = SystemClock.elapsedRealtime() - mChronometer.getBase();
        int hours = (int) (time/ 3600000);
        int minutes = (int) (time - hours * 3600000) / 60000;
        int seconds = (int) (time - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (time - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }

    public class MyBinder extends Binder{
        Bound getBound()
        {
            return Bound.this;
        }
    }
}
