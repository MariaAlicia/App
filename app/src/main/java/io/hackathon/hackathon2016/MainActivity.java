package io.hackathon.hackathon2016;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.orm.SugarContext;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import au.com.bytecode.opencsv.CSVReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        b1=(Button)findViewById(R.id.go);
        b2=(Button)findViewById(R.id.go2);
        // Spinner element
        final Spinner s = (Spinner) findViewById(R.id.spinner);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,s.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.route_content);
                ListView mainListView = (ListView) findViewById( R.id.mainListView );
                String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                        "Jupiter", "Saturn", "Uranus", "Neptune"};
                ArrayList<String> planetList = new ArrayList<String>();
                planetList.addAll( Arrays.asList(planets) );
                ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, planetList);

                listAdapter.add( "Ceres" );
                listAdapter.add( "Pluto" );
                listAdapter.add( "Haumea" );
                listAdapter.add( "Makemake" );
                listAdapter.add( "Eris" );

                mainListView.setAdapter( listAdapter );
            }
        });

        // Spinner click listener
        assert s != null;
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                String items=s.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        // Spinner Drop down elements
        List<String> route = new ArrayList<String>();//replace this with the getRoutes()
        route.add("Automobile");
        route.add("Business Services");
        route.add("Computers");
        route.add("Education");
        route.add("Personal");
        route.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, route);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        s.setAdapter(dataAdapter);

        //update database
	    DataController dataController = new DataController();
	    dataController.updateDatabase(getAssets());
    }

    public ArrayList getRoutes(){
        return new ArrayList(8);
    }
}
