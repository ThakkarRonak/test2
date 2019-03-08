package com.example.paresh.test.Test2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.paresh.test.R;

import java.util.Objects;

public class LoginFragment extends Fragment implements View.OnClickListener {
    public static final String myPreference = "mypref";
    public static final String EMAIL = "email_key";
    public static final String PSW = "psw_key";

    DbHelper helper;
    SharedPreferences preferences;
    EditText etEmail, etPsw;
    CheckBox checkBox;

    public LoginFragment() {// Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        etEmail = getView().findViewById(R.id.etEmail);
        etPsw = getView().findViewById(R.id.etPsw);
        checkBox = getView().findViewById(R.id.checkBox);
        Button btnLogin = getView().findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(myPreference, Context.MODE_PRIVATE);

        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(myPreference, Context.MODE_PRIVATE);

        if (preferences.contains(EMAIL) && preferences.contains(PSW)) {
            checkBox.setChecked(true);
            etEmail.setText(preferences.getString(EMAIL, ""));
            etPsw.setText(preferences.getString(PSW, ""));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                login();
                break;
        }
    }

    private void login() {
        final String email = etEmail.getText().toString();
        final String psw = etPsw.getText().toString();

        if (email.equals("jugal@gmail.com") && psw.equals("123456")) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(EMAIL, email);
            editor.putString(PSW, psw);
            editor.apply();
            editor.commit();

            Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
            nextfragment();

            /*helper = new DbHelper(getContext());
             helper.checkUser(email,psw);

           boolean isInserted = helper.insertUserDetail(email,psw);

           if (isInserted)
           {
               Toast.makeText(getActivity(), "Login Succesful", Toast.LENGTH_SHORT).show();
               nextfragment();

           }
           else {
               Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_SHORT).show();
           }
*/
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
            builder.setTitle("AlertBox");
            builder.setMessage("Invalid Login Details");
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(getActivity(), "try Again", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        }

    }

    private void nextfragment() {
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CategoryFragment fragment1 = new CategoryFragment();
        fragmentTransaction.replace(R.id.fragcontainer, fragment1)
                .commit();
    }
}
