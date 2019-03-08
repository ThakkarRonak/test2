package com.example.paresh.test.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.paresh.test.R;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    Button first_frag, secand_frag, btn_dilogFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        first_frag = findViewById(R.id.first_frag);
        secand_frag = findViewById(R.id.secand_frag);
        btn_dilogFrag = findViewById(R.id.btn_dilogFrag);

        first_frag.setOnClickListener(this);
        secand_frag.setOnClickListener(this);
        btn_dilogFrag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.first_frag:
                FirstFragment fragment = new FirstFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.layout_frg, fragment)
                        .commit();
                break;

            case R.id.secand_frag:
                SecandFragment secandFragment = new SecandFragment();
                FragmentManager manager1 = getSupportFragmentManager();
                manager1.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.layout_frg, secandFragment)
                        .commit();
                break;


            case R.id.btn_dilogFrag:

                FragmentManager fm = getSupportFragmentManager();
                FragmentForDialogtest fragmentForDialogtest = FragmentForDialogtest.newInstance("Fragment Title");
                fragmentForDialogtest.show(fm, " // Inflate the layout for this fragmentfragmnet_edit_name");

                break;
        }

    }

}




