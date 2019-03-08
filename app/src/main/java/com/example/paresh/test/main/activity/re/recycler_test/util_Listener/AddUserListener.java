package com.example.paresh.test.main.activity.re.recycler_test.util_Listener;

import com.example.paresh.test.main.activity.re.recycler_test.util_model.Userdata;

public interface AddUserListener {
    void onAddUser(Userdata userdata);
    void removeUser(int adapterPosition);
}
