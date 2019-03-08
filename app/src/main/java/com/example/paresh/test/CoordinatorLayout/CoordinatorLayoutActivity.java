package com.example.paresh.test.CoordinatorLayout;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.paresh.test.R;

public class CoordinatorLayoutActivity extends AppCompatActivity {
    private FloatingActionButton btn_snack;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);



        mCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.mCoordinatorLayout);
        btn_snack=(FloatingActionButton)findViewById(R.id.btn_snack);
        btn_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar();
            }
        });

    }

    private void showSnackBar() {

        Snackbar snackbar= Snackbar.make(mCoordinatorLayout,"This is snackbar",Snackbar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1= Snackbar.make(mCoordinatorLayout,"Undo Successful",Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                })
                .setActionTextColor(Color.GREEN);
        View snackview = snackbar.getView();
        snackbar.show();
    }


}
