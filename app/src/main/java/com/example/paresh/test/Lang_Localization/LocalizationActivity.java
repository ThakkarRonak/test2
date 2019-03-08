package com.example.paresh.test.Lang_Localization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.paresh.test.R;

public class LocalizationActivity extends AppCompatActivity {
TextView tv_h,tv_email,tv_psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization);


        tv_h =findViewById(R.id.tv_h);
        tv_email =findViewById(R.id.tv_email);
        tv_psw =findViewById(R.id.tv_psw);

    }
}
