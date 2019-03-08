package com.example.paresh.test.Test2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.EditText;
import com.example.paresh.test.R;

import java.util.ArrayList;

import static com.example.paresh.test.Test2.LoginActivity.EMAIL;
import static com.example.paresh.test.Test2.LoginActivity.PSW;
import static com.example.paresh.test.Test2.LoginActivity.myPreference;

public class CategoryFragment extends Fragment {
    RecyclerView rvCategory;
    MenuAdapter adapter;
    ArrayList<DataModel> menulist = new ArrayList<DataModel>();

    int parentCatId = 0;
    String emailId = "r@gmail.com";
    DbHelper helper;
    Test2Activity mActivity;
    SharedPreferences preferences;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (Test2Activity) getActivity();



        preferences = getActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);

        if (preferences.contains(EMAIL) && preferences.contains(PSW)) {
            emailId = preferences.getString(EMAIL, "");

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        rvCategory = view.findViewById(R.id.rvCategory);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("parentCatId")) {
            parentCatId = bundle.getInt("parentCatId", 0);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        helper = new DbHelper(getContext());

        setCategoryData();
    }

    private void setCategoryData() {

        menulist.clear();
        menulist.addAll(helper.getalldata(parentCatId, emailId));

        if (adapter == null) {
            adapter = new MenuAdapter(menulist, mActivity, this);

            rvCategory.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void showAlert(final int catId) {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Add New");
        LayoutInflater inflater = this.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_dailog, null);
        dialog.setView(alertLayout);
        final AlertDialog alert = dialog.show();

        final EditText etData = alertLayout.findViewById(R.id.etData);
        alertLayout.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etData.getText().toString();
                helper.insertCategory(catId, item,emailId);
                adapter.notifyDataSetChanged();
                helper.close();
                alert.dismiss();
                setCategoryData();
            }
        });
        alertLayout.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = etData.getText().toString();
                helper.insertCategory(catId, item,emailId);
                adapter.notifyDataSetChanged();
                helper.close();
                alert.dismiss();
                setCategoryData();
            }
        });
    }
}