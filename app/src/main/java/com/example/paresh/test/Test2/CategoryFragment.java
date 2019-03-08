package com.example.paresh.test.Test2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;
import com.example.paresh.test.R;

import java.util.ArrayList;
import java.util.Objects;

public class CategoryFragment extends Fragment {
    RecyclerView rvCategory;
    MenuAdapter adapter;
    ArrayList<DataModel> menulist = new ArrayList<DataModel>();

    int parentCatId = 0;
    DbHelper helper;
    Test2Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (Test2Activity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        /*MenuAdapter menuAdapter =new MenuAdapter(menulist1);
        rvMenu.setAdapter(menuAdapter);*/

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
//        tvMsg = (getView()).findViewById(R.id.tvmsg);
//        tvClick = (getView()).findViewById(R.id.tvClick);

        helper = new DbHelper(getContext());

        setCategoryData();
    }

    private void setCategoryData() {

        menulist.clear();
        menulist.addAll(helper.getalldata(parentCatId));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item_add:
                showAlert(parentCatId);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlert(final int catId) {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        dialog.setTitle("Add New");
        LayoutInflater inflater = this.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_dailog, null);
        dialog.setView(alertLayout);

        final EditText etData = alertLayout.findViewById(R.id.etData);
        View btnAdd = alertLayout.findViewById(R.id.btnAdd);
        View btnCancel = alertLayout.findViewById(R.id.btnCancel);
        final AlertDialog alert = dialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Close", Toast.LENGTH_SHORT).show();
                alert.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = etData.getText().toString();
                    helper.insertCategory(catId, item);
//                helper.getalldata();
                adapter.notifyDataSetChanged();
                helper.close();
                alert.dismiss();

                setCategoryData();
            }
        });
        dialog.setCancelable(true);
    }
}