package com.example.paresh.test.listview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.paresh.test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {
  private Planet[] planets;
  private ArrayAdapter<Planet> listAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    ListView lv_mylist = (ListView)findViewById(R.id.lv_mylist);
    lv_mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Planet planet = listAdapter.getItem(position);
        PlanetViewHolder viewHolder = (PlanetViewHolder) view.getTag();
        viewHolder.getCheckBox().setChecked( planet.isChecked() );
      }
    });

    planets = (Planet[]) getLastCustomNonConfigurationInstance() ;
    if ( planets == null ) {
      planets = new Planet[] {
        new Planet("Mercury"), new Planet("Venus"), new Planet("Earth"),
        new Planet("Mars"), new Planet("Jupiter"), new Planet("Saturn"),
        new Planet("Uranus"), new Planet("Neptune"), new Planet("Ceres"),
        new Planet("Pluto"), new Planet("Haumea"), new Planet("Makemake"),
        new Planet("Eris")
      };
    }
    ArrayList<Planet> planetList = new ArrayList<Planet>();
    planetList.addAll(Arrays.asList(planets));


    listAdapter = new PlanetArrayAdapter(this, planetList);
    lv_mylist.setAdapter( listAdapter );
  }

  /** Holds planet data. */
  private static class Planet {
    private String name = "" ;
    private boolean checked = false ;
    public Planet() {}
    public Planet( String name ) {
      this.name = name ;
    }
    public Planet( String name, boolean checked ) {
      this.name = name ;
      this.checked = checked ;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public boolean isChecked() {
      return checked;
    }
    public void setChecked(boolean checked) {
      this.checked = checked;
    }
    public String toString() {
      return name ;
    }
    public void toggleChecked() {
      checked = !checked ;
    }
  }


  private static class PlanetViewHolder {
    private CheckBox checkBox ;
    private TextView textView ;
    public PlanetViewHolder() {}
    public PlanetViewHolder( TextView textView, CheckBox checkBox ) {
      this.checkBox = checkBox ;
      this.textView = textView ;
    }
    public CheckBox getCheckBox() {
      return checkBox;
    }
    public void setCheckBox(CheckBox checkBox) {
      this.checkBox = checkBox;
    }
    public TextView getTextView() {
      return textView;
    }
    public void setTextView(TextView textView) {
      this.textView = textView;
    }
  }

  /** Custom adapter for displaying an array of Planet objects. */
  private static class PlanetArrayAdapter extends ArrayAdapter<Planet> {

    private LayoutInflater inflater;

    public PlanetArrayAdapter(Context context, List<Planet> planetList ) {
      super( context, R.layout.pl_row, R.id.rowTextView, planetList );
      // Cache the LayoutInflate to avoid asking for a new one each time.
      inflater = LayoutInflater.from(context) ;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      Planet planet = this.getItem( position );
      CheckBox checkBox ;
      TextView textView ;

      if ( convertView == null ) {
        convertView = inflater.inflate(R.layout.pl_row, null);

        textView = (TextView) convertView.findViewById( R.id.rowTextView );
        checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox01 );
        convertView.setTag( new PlanetViewHolder(textView,checkBox) );


        checkBox.setOnClickListener( new View.OnClickListener() {
          public void onClick(View v) {
            CheckBox cb = (CheckBox) v ;
            Planet planet = (Planet) cb.getTag();
            planet.setChecked( cb.isChecked() );
          }
        });
      }

      else {
        PlanetViewHolder viewHolder = (PlanetViewHolder) convertView.getTag();
        checkBox = viewHolder.getCheckBox() ;
        textView = viewHolder.getTextView() ;
      }
      checkBox.setTag( planet );

      checkBox.setChecked( planet.isChecked() );
      textView.setText( planet.getName() );
      return convertView;
    }

  }

  public Object onRetainCustomNonConfigurationInstance () {
    return planets ;
  }
}



