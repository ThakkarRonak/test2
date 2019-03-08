package com.example.paresh.test.SQLite_CRUD;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.paresh.test.R;

public class DbOperation_crudActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnDelete;
    Button btnViewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_operation_crud);
        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editSurname = (EditText) findViewById(R.id.editText_surname);
        editMarks = (EditText) findViewById(R.id.editText_Marks);
        editTextId = (EditText) findViewById(R.id.editText_id);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnViewAll = (Button) findViewById(R.id.button_viewAll);
        btnViewUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void deleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(DbOperation_crudActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DbOperation_crudActivity.this, "Enter ID For Delete", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void updateData() {
        btnViewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(), editMarks.getText().toString());

                        if (editTextId.getText().toString().isEmpty()) {
                            Toast.makeText(DbOperation_crudActivity.this, "Enter Id for Update", Toast.LENGTH_LONG).show();
                        } else if (isUpdate == true) {
                            Toast.makeText(DbOperation_crudActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        }
                    }

                }
        );
    }

    public void addData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = editName.getText().toString();
                        String surname = editSurname.getText().toString();
                        String mark = editMarks.getText().toString();

                        if (name.isEmpty() && surname.isEmpty() && mark.isEmpty()) {
                            Toast.makeText(DbOperation_crudActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();

                        }else {

                            boolean isInserted = myDb.insertData(name,
                                    surname,
                                    mark);
                            if (isInserted == true)

                                Toast.makeText(DbOperation_crudActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(DbOperation_crudActivity.this, "Enter Data To Insert", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }

    public void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id      :" + res.getString(0) + "\n");
                            buffer.append("Name    :" + res.getString(1) + "\n");
                            buffer.append("Surname :" + res.getString(2) + "\n");
                            buffer.append("Marks   :" + res.getString(3) + "\n\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            finish();
            return true; }
        return super.onOptionsItemSelected(item);
    }
}


