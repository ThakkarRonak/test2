package com.example.paresh.test.Test2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import com.example.paresh.test.R;

import java.util.ArrayList;

public class MenuFragment extends Fragment {
    RecyclerView rvmenu_menu;
    MyAdapter adapter1;
    ArrayList<DataModel> menulist1 = new ArrayList<DataModel>();

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

        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        rvmenu_menu = view.findViewById(R.id.rvmenu_menu);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("parentCatId")) {
            parentCatId = bundle.getInt("parentCatId", 0);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvmenu_menu.setLayoutManager(new LinearLayoutManager(getContext()));

        helper = new DbHelper(getContext());
        setMenuData();
    }

    private void setMenuData() {

        menulist1.clear();
        menulist1.addAll(helper.getalldata(parentCatId));

        if (adapter1 == null) {
            adapter1 = new MyAdapter(menulist1, mActivity);

            rvmenu_menu.setAdapter(adapter1);
        } else {


            adapter1.notifyDataSetChanged();
        }
    }

  }