package com.example.paresh.test.Dilogs;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.example.paresh.test.R;

public class AlertDilog_Activity extends AppCompatActivity {
    Button btn_Logout, btn_download, btn_Login, frg_dilog;
    CheckBox cbToggle;
    EditText et_username, et_psw;
    private ProgressBar progress;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dilog_);
        btn_Logout = findViewById(R.id.btn_Logout);
        btn_download = findViewById(R.id.btn_download);
        et_username = findViewById(R.id.et_username);
        et_psw = findViewById(R.id.et_psw);
        btn_Login = findViewById(R.id.btn_Login);


        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = AlertDilog_Activity.this.getLayoutInflater();

                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDilog_Activity.this);
                builder.setView(inflater.inflate(R.layout.layout_custom_dialog, null));
                builder.setMessage("Alert");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDilog_Activity.this, "Clicked Ys", Toast.LENGTH_SHORT).show();
                    }
                })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDilog_Activity.this, "Clicked No", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog progressDialog = new ProgressDialog(AlertDilog_Activity.this);
                progressDialog.setMessage("Downloading.....");
                progressDialog.setTitle("ProgressDialog");
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
                progressDialog.show();
            }
        });

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);

                final EditText etUsername = alertLayout.findViewById(R.id.et_username);
                final EditText etEmail = alertLayout.findViewById(R.id.et_psw);
                /*final CheckBox cbToggle = alertLayout.findViewById(R.id.cb_show_pass);
*/
               /* cbToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            etEmail.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            etEmail.setTransformationMethod(null);
                        }
                    }
                });*/

                AlertDialog.Builder alert = new AlertDialog.Builder(AlertDilog_Activity.this);
                alert.setTitle("Info")
                        .setCancelable(false)
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Yes ", Toast.LENGTH_SHORT).show();
                            }
                        });

                alert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Exit ", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });


    }

}