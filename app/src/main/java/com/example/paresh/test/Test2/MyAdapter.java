package com.example.paresh.test.Test2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.paresh.test.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    MenuFragment menuFragment;
    private ArrayList<DataModel> menuList;
    private Test2Activity activity;

    public MyAdapter(ArrayList<DataModel> mMenulist, Test2Activity test2Activity) {
        this.menuList = mMenulist;
        this.activity = test2Activity;
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
        holder.tvData.setTag(i);
        holder.tvData.setText(model.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvData;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvData = itemView.findViewById(R.id.tvData);
            tvData.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(activity, "click", Toast.LENGTH_SHORT).show();
//            activity.addView(menuList.get((Integer) v.getTag()));
//            activity.menuAdapter.notifyDataSetChanged();



        }
    }

}