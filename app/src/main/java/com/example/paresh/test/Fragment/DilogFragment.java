package com.example.paresh.test.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.paresh.test.R;


public class DilogFragment extends Fragment {

    public static final String TAG ="mainfragment";
    private Button btn;

    public DilogFragment() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.layout_custom_dialog,container, false);
    }


    }

