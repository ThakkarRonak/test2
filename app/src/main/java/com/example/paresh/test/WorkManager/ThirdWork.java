package com.example.paresh.test.WorkManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ThirdWork extends Worker {

    public static final String THIRD_OUTPUT = "third_output";

    String TAG = ThirdWork.class.getSimpleName();

    public ThirdWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG,"doWork: Third Work Completed");

        Data data1 = new Data.Builder()
                .putString(THIRD_OUTPUT,"Third Task Is Finished Successful ")
                .build();
        setOutputData(data1);

        return Result.SUCCESS;
    }
}
