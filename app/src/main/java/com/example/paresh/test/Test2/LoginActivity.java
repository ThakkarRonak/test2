package com.example.paresh.test.Test2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.paresh.test.R;

public class LoginActivity extends AppCompatActivity {
    public static final String myPreference = "mypref";
    public static final String EMAIL = "email_key";
    public static final String PSW = "psw_key";

    SharedPreferences preferences;
    EditText etEmail, etPsw;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPsw = findViewById(R.id.etPsw);
        checkBox = findViewById(R.id.checkBox);

        preferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);

        if (preferences.contains(EMAIL) && preferences.contains(PSW)) {
            checkBox.setChecked(true);
            etEmail.setText(preferences.getString(EMAIL, ""));

        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragmentCurrent = fragmentManager.findFragmentById(R.id.fragcontainer);

        if (fragmentCurrent != null) {
            fragmentTransaction.hide(fragmentCurrent);
        }
        findViewById(R.id.btnLogin_DB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_DB();
            }
        });
    }

    private void login_DB() {
        final String email = etEmail.getText().toString();
        final String psw = etPsw.getText().toString();

        if ((email.equals("jugal@gmail.com") && psw.equals("123456")) || (email.equals("r@gmail.com") && psw.equals("111"))) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(EMAIL, email);
            editor.putString(PSW, psw);
            editor.apply();
            editor.commit();

            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, Test2Activity.class));

        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("AlertBox");
            builder.setMessage("Invalid Login Details");
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(LoginActivity.this, "Please try Again", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        }

    }

}
