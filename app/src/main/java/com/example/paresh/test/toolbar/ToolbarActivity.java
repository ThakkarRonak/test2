package com.example.paresh.test.toolbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.paresh.test.R;

public class ToolbarActivity extends AppCompatActivity {
 ActionMode actionMode;
Button button;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_toolbar);

    button =findViewById(R.id.btn_copy);

    Toolbar toolbar =findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar bar =getSupportActionBar();

    bar.setDisplayHomeAsUpEnabled(true);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          actionMode = ToolbarActivity.this.startActionMode(new Actionbarcallback());
      }
    });
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu,menu);

    MenuItem searchItem = menu.findItem(R.id.action_serach);
    SearchView searchView = (SearchView)searchItem.getActionView();

    return super.onCreateOptionsMenu(menu);
  }



  class Actionbarcallback implements ActionMode.Callback{

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {

      mode.getMenuInflater().inflate(R.menu.menu_action,menu);
      return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

      return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {


      return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
  }
  }

