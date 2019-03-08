package com.example.paresh.test.main.activity.re.recycler_test.activity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;
import com.example.paresh.test.R;
import com.example.paresh.test.main.activity.re.recycler_test.alertbox.alertDilogbox;
import com.example.paresh.test.main.activity.re.recycler_test.util_Listener.AddUserListener;
import com.example.paresh.test.main.activity.re.recycler_test.util_Adapter.Useradapter;
import com.example.paresh.test.main.activity.re.recycler_test.util_Validation.validation;
import com.example.paresh.test.main.activity.re.recycler_test.util_model.Userdata;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AddUserListener {

    EditText et_name,et_email,et_add;
    RadioButton rb_f, rb_m;
    RadioGroup rbGroup;
    Button btn_aduser;
    Button btn_add;
    ImageButton btn_close,ib_clear;
    RecyclerView userRecyclerView;

    ArrayList<Userdata> userdataList;
    Useradapter useradapter;
    String selectgender;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name =findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        rb_m = findViewById(R.id.rb_m);
        rb_f = findViewById(R.id.rb_f);
        rbGroup = findViewById(R.id.rbGroup);
        et_email = findViewById(R.id.et_email);
        et_add = findViewById(R.id.et_add);
        btn_aduser = findViewById(R.id.btn_aduser);
        btn_add = findViewById(R.id.btn_add);
        btn_close = (ImageButton)findViewById(R.id.btn_close);
        userRecyclerView =(RecyclerView)findViewById(R.id.rv_user);
        ib_clear=findViewById(R.id.ib_clear);

        userdataList = new ArrayList<>();
        setUpRecyclerView();
        btn_aduser.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        ib_clear.setOnClickListener(this);

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_f) { selectgender = "female"; }

                else { selectgender = "male"; } }});
        }

        private void setUpRecyclerView() {
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        useradapter = new Useradapter(userdataList, this);
        userRecyclerView.setAdapter(useradapter);
    }

    private boolean checkvalidation() {

        final String name = et_name.getText().toString();
        final String email = et_email.getText().toString();
        final String address = et_add.getText().toString().trim();


        if (validation.isValidname(name)) {
            alertDilogbox.showAlert001(this, "Please enter valid name");
            return false;
        } else if (validation.isValidEmail(email)) {
            alertDilogbox.showAlert001(this, "Please enter valid  Email");
            return false;
        } else if (validation.isvalidaddress(address)) {
            alertDilogbox.showAlert001(this, "Please enter valid address");
            return false;
        } else { }
        return true;
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_aduser:
                if (checkvalidation()) {
                    Userdata userdata = new Userdata();
                    userdata.setName(et_name.getText().toString());
                     userdata.setEmail(et_email.getText().toString());
                    userdata.setAdd(et_add.getText().toString());
                    userdata.setSelectgender(selectgender);
                    userdataList.add(userdata);
                    useradapter.notifyDataSetChanged();
                }
                break;

            case R.id.btn_close:

                AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do You Want to Exit?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { finish(); }})
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel(); }});
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

              case R.id.ib_clear:

                  userdataList.clear();
                  useradapter.notifyDataSetChanged();
                  break;
        }
    }
    @Override
    public void onAddUser(Userdata userdata) {
        userdataList.add(userdata);
        useradapter.notifyDataSetChanged();
    }
    @Override
    public void removeUser(int adapterPosition) {
        userdataList.remove(adapterPosition);
        useradapter.notifyDataSetChanged();
    }
}
