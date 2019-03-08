package com.example.paresh.test.WorkManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.paresh.test.R;

public class MyWorker extends Worker {

    public static final String TASK_OUTPUT = "task_output";
    String TAG = MyWorker.class.getSimpleName();

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams)    { super(context, workerParams); }
    @NonNull
    @Override
    public Result doWork() {

        Log.d(TAG , "doWork: First Work");


        Data data  = getInputData();
        String desc =data.getString(WorkmanagerexampleActivity.TASK_DESC);

            displayNotification("My Worker",desc);

        Data data1 = new Data.Builder()
                .putString(TASK_OUTPUT,"Task Is Finished Successful ")
                .build();
        setOutputData(data1);


        return Result.SUCCESS;
        }

    private void displayNotification(String title,String task)
    {
        NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, notification.build());

    }
}

