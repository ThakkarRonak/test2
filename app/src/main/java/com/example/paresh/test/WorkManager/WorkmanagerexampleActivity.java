package com.example.paresh.test.WorkManager;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.work.*;
import com.example.paresh.test.R;

public class WorkmanagerexampleActivity extends AppCompatActivity {

    public static final String TASK_DESC = "task_desc";
    String TAG = MyWorker.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workmanagerexample);

        Data data = new Data.Builder()
                .putString(TASK_DESC, "Data Was Sending")
                .build();

//        Constraints constraints = new Constraints.Builder()
//                .setRequiresCharging(true)
//                 .setRequiresStorageNotLow(true)
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build();

//        final PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class,5,TimeUnit.SECONDS)
//                .setInputData(data)
//                .setConstraints(constraints)
//                .build();
        final WorkManager mworkmanager = WorkManager.getInstance();

        final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .addTag("first")
//                .setConstraints(constraints)
                .build();

        final OneTimeWorkRequest workRequest2 = new OneTimeWorkRequest.Builder(SecandWork.class)
                .build();

        final OneTimeWorkRequest workRequest3 = new OneTimeWorkRequest.Builder(ThirdWork.class)
                .build();


        findViewById(R.id.btn_enque).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WorkContinuation continuation = mworkmanager.beginWith(workRequest1);
                continuation.then(workRequest2)
                        .then(workRequest3)
                        .enqueue();

                /* WorkManager.getInstance()
                        .beginWith(workRequest1)
                        .then(workRequest2)
                        .enqueue();*/
                }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Work Was canceled");
                WorkManager.getInstance().cancelAllWorkByTag("first");
            }
        });

        final TextView textView = findViewById(R.id.tv_status);
        final TextView stuts = findViewById(R.id.tv_status);

        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest1.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {

                        if (workInfo != null) {
                            if (workInfo.getState().isFinished()) {

                                Data data = workInfo.getOutputData();
                                String output = data.getString(MyWorker.TASK_OUTPUT);
                                textView.append(output + "\n");

                                String output1 = data.getString(SecandWork.SEC_OUTPUT);
                                textView.append(output1 + "\n");

                                String output2 = data.getString(ThirdWork.THIRD_OUTPUT);
                                textView.append(output2 + "\n");
                            }
                            String stat = workInfo.getState().name();
                            textView.append(stat + "\n"); }
                    }
                });

    }
}
