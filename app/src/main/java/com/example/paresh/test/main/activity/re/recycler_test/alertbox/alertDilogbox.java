package com.example.paresh.test.main.activity.re.recycler_test.alertbox;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;


public class alertDilogbox {

    public static void showAlert001(Context mContext, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
