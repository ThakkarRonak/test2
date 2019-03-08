package com.example.paresh.test.WorkManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SecandWork extends Worker {

    String TAG = SecandWork.class.getSimpleName();

    public static final String SEC_OUTPUT = "sec_output";

    public SecandWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.d(TAG, "doWork: Secand Work Completed");
        Data data1 = new Data.Builder()
                .putString(SEC_OUTPUT,"Secand Task Is Finished Successful ")
                .build();
        setOutputData(data1);

        return Result.SUCCESS;
    }

}
