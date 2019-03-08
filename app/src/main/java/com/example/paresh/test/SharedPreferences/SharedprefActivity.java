package com.example.paresh.test.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.example.paresh.test.R;

public class SharedprefActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView name,email;

    public static final String myPreference = "mypref";
    public static final String Name ="name_key";
    public static final String Email ="email_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpref);

        name=findViewById(R.id.etName);
        email=findViewById(R.id.etEmail);
         sharedPreferences =getSharedPreferences(myPreference,Context.MODE_PRIVATE);

         if (sharedPreferences.contains(Name))
         {
             name.setText(sharedPreferences.getString(Name,"")); }

         if (sharedPreferences.contains(Email))
         {
             email.setText(sharedPreferences.getString(Email,"")); }

             }

    public void Save(View v)
    {
        String n= name.getText().toString();
        String e =email.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Name,n);
        editor.putString(Email,e);
        editor.commit();
    }
    public void clear(View view){
        name=findViewById(R.id.etName);
        email=findViewById(R.id.etEmail);
        name.setText("");
        email.setText(""); }

    public void Get(View view){
        name=findViewById(R.id.etName);
        email=findViewById(R.id.etEmail);

        sharedPreferences =getSharedPreferences(myPreference,Context.MODE_PRIVATE);

        if (sharedPreferences.contains(Name)) {
            name.setText(sharedPreferences.getString(Name, ""));
        }
        if (sharedPreferences.contains(Email)) {
            email.setText(sharedPreferences.getString(Email, ""));

        } }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}












