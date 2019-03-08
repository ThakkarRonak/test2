package com.example.paresh.test.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pager extends FragmentPagerAdapter {

    int tabcount;


    public Pager(FragmentManager fm,int tabcount) {
        super(fm);
        this.tabcount = tabcount; }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;

                default:
                    return null;
        }


    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
