package com.example.paresh.test.Test2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.paresh.test.R;

import java.util.ArrayList;

public class Test2Activity extends AppCompatActivity {

    private static final String TAG = Test2Activity.class.getSimpleName();
    ArrayList<DataModel> mMenulist = new ArrayList<DataModel>();
    ArrayList<DataModel> mMenulist1 = new ArrayList<DataModel>();
    RecyclerView menu_recyclerview;
    MyAdapter adapter;
    MenuAdapter menuAdapter;
    CategoryFragment categoryFragment = new CategoryFragment();
    TextView txttitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        menu_recyclerview = findViewById(R.id.menu_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Test2Activity.this, LinearLayoutManager.VERTICAL, false);
        menu_recyclerview.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(mMenulist, Test2Activity.this);
        menuAdapter = new MenuAdapter(mMenulist1, Test2Activity.this);
        menu_recyclerview.setAdapter(adapter);
        addCategoryFragment(0);

        txttitle = findViewById(R.id.txttitle);

        findViewById(R.id.img_add_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Fragemtb Present" + isFragmentPresent("FRAG"));

                if (isFragmentPresent("FRAG")) {
                    Fragment fragmentCurrent = getSupportFragmentManager().findFragmentById(R.id.fragcontainer);
                    categoryFragment = (CategoryFragment) fragmentCurrent;
                    categoryFragment.showAlert(0);
                }
            }
        });
        findViewById(R.id.img_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Test2Activity.this)
                        .setTitle("Are You Sure You want Logout")
                        .setMessage("Logout ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Test2Activity.this, "Yes on Logout", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Test2Activity.this,LoginActivity.class));
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Test2Activity.this, "NO On Logout", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

            }
        });



    }

    public boolean isFragmentPresent(String tag) {
        Fragment frag = getSupportFragmentManager().findFragmentByTag(tag);
        if (frag instanceof CategoryFragment) {
            return true;
        } else
            return false;

    }

    public void addCategoryFragment(int parentCatId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        categoryFragment = new CategoryFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("parentCatId", parentCatId);

        categoryFragment.setArguments(bundle);

        Fragment fragmentCurrent = fragmentManager.findFragmentById(R.id.fragcontainer);

        if (fragmentCurrent != null) {
            fragmentTransaction.hide(fragmentCurrent);
        }
        if (parentCatId == 0) {
            fragmentTransaction.replace(R.id.fragcontainer, categoryFragment, "FRAG");
        } else {
            fragmentTransaction.addToBackStack("parentCatId" + parentCatId);

            fragmentTransaction.add(R.id.fragcontainer, categoryFragment, "FRAG");
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        removeView(1);
        txttitle.setText(adapter.model.getCategoryName());

    }

    public void addView(DataModel dataModel) {
        mMenulist.add(dataModel);
        adapter.notifyDataSetChanged();
    }


    public void removeView(int poaition) {
        if (mMenulist.size() == 1) return;

        mMenulist.remove(1);
        adapter.notifyDataSetChanged();
        mMenulist.size();
    }
}
