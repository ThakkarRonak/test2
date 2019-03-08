package com.example.paresh.test.JobScheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.paresh.test.R;

public class JobSchedulerActivity extends AppCompatActivity {
    private static final String TAG = "JobSchedulerActivity";
    Button startJob, stopJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);


        startJob = findViewById(R.id.btn_startjob);
        stopJob = findViewById(R.id.btn_stopjob);




       startJob.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              StartJob();

           }
       });
        stopJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StopJob();

            }
        });

    }

    private void StartJob() {
        ComponentName componentName = new ComponentName(this,SchedualjobExample.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_CELLULAR)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        int resultcode = scheduler.schedule(info);

        if (resultcode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job Scheduler");
        } else {
            Log.d(TAG, "Job Scheduling Failed");
        }

    }

    private void StopJob() {
        JobScheduler  scheduler =(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG,"Job Scheduler Cancel");
    }

}
