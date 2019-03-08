package com.example.paresh.test.Test2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.paresh.test.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    CategoryFragment categoryFragment;
    private ArrayList<DataModel> menuList;
    private Test2Activity activity;

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

        if (childCatCount == 0) {
//            holder.tvData.setText(model.getCategoryName() + "    +   ");
            holder.tvData.setText(model.getCategoryName());
            holder.img_add.setVisibility(View.VISIBLE);


            activity.setTitleText(holder.tvData.getText());

//((TextView)(activity.getSupportActionBar().getCustomView()).findViewById(R.id.title)).setText((holder.tvData.getText()));

        } else {
            holder.imag_see.setVisibility(View.VISIBLE);
            holder.img_add.setVisibility(View.GONE);
            holder.tvData.setText(model.getCategoryName());
//            holder.tvData.setText(model.getCategoryName() + "    >   ");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childCatCount == 0) {
                    categoryFragment.showAlert(model.getCategoryID());
                } else {
                    activity.addView(model);
                    activity.addCategoryFragment(model.getCategoryID());
                }

                activity.adapter.notifyDataSetChanged();
            }
        });
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

        }

    }

}