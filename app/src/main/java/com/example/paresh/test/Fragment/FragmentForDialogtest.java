package com.example.paresh.test.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.*;
import android.widget.EditText;
import com.example.paresh.test.R;

public class FragmentForDialogtest extends DialogFragment {
    private EditText mEditText;


    public FragmentForDialogtest() {


    }

    public static FragmentForDialogtest newInstance(String title) {
        Bundle args = new Bundle();
        FragmentForDialogtest fragment = new FragmentForDialogtest();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
//        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
//        getDialog().getWindow().setLayout(width, height);


//        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getDialog().setCanceledOnTouchOutside(true);
        return inflater.inflate(R.layout.layout_custom_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.txt_your_name);

    }
}