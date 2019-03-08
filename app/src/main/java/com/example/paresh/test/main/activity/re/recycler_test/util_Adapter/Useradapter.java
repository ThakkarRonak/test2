package com.example.paresh.test.main.activity.re.recycler_test.util_Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.paresh.test.main.activity.re.recycler_test.util_Listener.AddUserListener;
import com.example.paresh.test.R;
import com.example.paresh.test.main.activity.re.recycler_test.util_model.Userdata;

import java.util.ArrayList;

public class Useradapter extends RecyclerView.Adapter<Useradapter.myViewHolder> {

    private ArrayList<Userdata> user;
    private AddUserListener addUserListener;

    public Useradapter(ArrayList<Userdata> user, AddUserListener addUserListener) {
        this.user = user;
        this.addUserListener = addUserListener; }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int i) {
        final Userdata udata = user.get(i);

        holder.tv_name.setText(udata.getName());
        holder.tv_email.setText(udata.getEmail());
        holder.tv_adddress.setText(udata.getAdd());

        if (udata.getSelectgender().equalsIgnoreCase("female")) {
            holder.iv_img.setImageResource(R.drawable.iconfinder_female_1608695);
        } else {
            holder.iv_img.setImageResource(R.drawable.iconfinder_male_1608532);
        }

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                if (addUserListener != null){
                                                    addUserListener.onAddUser(udata);
                                                } }});

        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  if (addUserListener != null){
                                                      addUserListener.removeUser(holder.getAdapterPosition());
                                                  } }});
        }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public final TextView tv_name, tv_email, tv_adddress;

        ImageView iv_img;
        Button btn_add, btn_remove;

        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_adddress = itemView.findViewById(R.id.tv_adddress);
            iv_img = itemView.findViewById(R.id.iv_img);
            btn_add = itemView.findViewById(R.id.btn_add);
            btn_remove = itemView.findViewById(R.id.btn_remove);

        }
    }

}
