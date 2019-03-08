package com.example.paresh.test.AsyncTask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.paresh.test.R;

public class AsyncTaskActivity extends AppCompatActivity {
    private Button button;
    private EditText time;
    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);


        time =findViewById(R.id.et_intime);
        button=findViewById(R.id.btn_run);
        finalResult=findViewById(R.id.tv_result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleeptime = time.getText().toString();
                runner.execute(sleeptime);


            }
        }); }


    private class AsyncTaskRunner extends AsyncTask<String,String,String>{

        private  String resp ;
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... params) {

            publishProgress("Sleeping");
            try{
                int time =Integer.parseInt(params[0])*1000;
                Thread.sleep(time);
                resp= "Slept for " + params[0] + " seconds";
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            catch (Exception e) {
                e.printStackTrace();
                resp =e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = progressDialog.show(AsyncTaskActivity.this,
                    "ProgressDialog","Wait for"+time.getText().toString()+"secadns");
        }
    }
}
