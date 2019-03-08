package com.example.paresh.test.Test2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.example.paresh.test.R;

import java.util.ArrayList;

public class Test2Activity extends AppCompatActivity {

    ArrayList<DataModel> mMenulist = new ArrayList<DataModel>();
    RecyclerView menu_recyclerview;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        menu_recyclerview = findViewById(R.id.menu_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Test2Activity.this, LinearLayoutManager.VERTICAL, false);
        menu_recyclerview.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(mMenulist, Test2Activity.this);
        menu_recyclerview.setAdapter(adapter);
        addCategoryFragment(0);
    }

    public void addCategoryFragment(int parentCatId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CategoryFragment fragment = new CategoryFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("parentCatId", parentCatId);

        fragment.setArguments(bundle);

        Fragment fragmentCurrent = fragmentManager.findFragmentById(R.id.fragcontainer);

        if (fragmentCurrent != null) {
            fragmentTransaction.hide(fragmentCurrent);
        }
        if (parentCatId == 0) {
            fragmentTransaction.replace(R.id.fragcontainer, fragment);
        }
        else {
            fragmentTransaction.addToBackStack("parentCatId" + parentCatId);
            fragmentTransaction.add(R.id.fragcontainer, fragment);
        }
        fragmentTransaction.commit();
    }

    public void addView(DataModel dataModel) {
        mMenulist.add(dataModel);
        adapter.notifyDataSetChanged();
    }
    public void removeView(DataModel dataModel) {
        mMenulist.add(dataModel);
        adapter.notifyDataSetChanged();
    }

    public void setTitleText(CharSequence text) {
        ((TextView)findViewById(R.id.txttitle)).setText(text);
    }

    /*public void addMenuFragment(int parentCatId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MenuFragment fragment = new MenuFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("parentCatId", parentCatId);

        fragment.setArguments(bundle);

        Fragment fragmentCurrent = fragmentManager.findFragmentById(R.id.flMenu);

        if (fragmentCurrent != null) {
            fragmentTransaction.hide(fragmentCurrent);
        }
        fragmentTransaction.addToBackStack("parentCatId" + parentCatId);
        fragmentTransaction.add(R.id.flMenu, fragment);
        fragmentTransaction.commit();
    }*/

}
