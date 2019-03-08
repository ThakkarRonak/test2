package com.example.paresh.test.Test2;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.paresh.test.R;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    CategoryFragment categoryFragment;
    private ArrayList<DataModel> menuList;
    private Test2Activity activity;
    SharedPreferences preferences;


    public MenuAdapter(ArrayList<DataModel> menulist, Test2Activity mActivity) {
        this.menuList = menulist;
        this.activity = mActivity;
    }

    public MenuAdapter(ArrayList<DataModel> menulist, Test2Activity mActivity, CategoryFragment categoryFragment) {
        this.menuList = menulist;
        this.activity = mActivity;
        this.categoryFragment = categoryFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        final DataModel model = menuList.get(i);

        final int childCatCount = categoryFragment.helper.getalldataCount(model.categoryID);
        holder.tvData.setTag(i);

        if (childCatCount == 0) {
            holder.tvData.setText(model.getCategoryName());
            holder.img_add.setVisibility(View.VISIBLE);

            activity.setTitleText(holder.tvData.getText());
        } else {
            holder.imag_see.setVisibility(View.VISIBLE);
            holder.img_add.setVisibility(View.GONE);
            holder.tvData.setText(model.getCategoryName());
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvData;
        ImageView img_add, imag_see;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvData = itemView.findViewById(R.id.tvData);
            img_add = itemView.findViewById(R.id.imag_add);
            imag_see = itemView.findViewById(R.id.imag_see);

            tvData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final int childCatCount = categoryFragment.helper.getalldataCount(menuList.get((Integer) v.getTag()).categoryID);

                    if (childCatCount == 0) {
                        categoryFragment.showAlert(menuList.get((Integer) v.getTag()).getCategoryID());
                        Log.i(TAG, "onClick: ");

                    } else {
                        activity.addView(menuList.get((Integer) v.getTag()));

                        activity.addCategoryFragment(menuList.get((Integer) v.getTag()).getCategoryID());

                    }
                    activity.adapter.notifyDataSetChanged();
                }
            });

        }


    }

}