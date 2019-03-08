package com.example.paresh.test.main.activity.re.recycler_test.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.paresh.test.R;
import com.example.paresh.test.main.activity.re.recycler_test.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {
TextView Tv_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Tv_welcome=findViewById(R.id.Tv_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}
