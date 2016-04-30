package io.hackathon.hackathon2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	    DataController dataController = new DataController();

        //update database
        dataController.updateDatabase(getAssets());
        
        b1=(Button)findViewById(R.id.go);
        b2=(Button)findViewById(R.id.go2);
        // Spinner element
        final Spinner s = (Spinner) findViewById(R.id.spinner);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.route_content);
                ListView mainListView = (ListView) findViewById( R.id.mainListView );
                String[] times = new String[] { "Mercury", "Venus", "Earth", "Mars",
                        "Jupiter", "Saturn", "Uranus", "Neptune"};//from method
                ArrayList<String> busTimes = new ArrayList<String>();
                busTimes.addAll( Arrays.asList(times));
                ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, busTimes);

                listAdapter.add( "Ceres" );//if method to get all times at this stop works
                listAdapter.add( "Pluto" );//then these adds are not needed
                listAdapter.add( "Haumea" );
                listAdapter.add( "Makemake" );
                listAdapter.add( "Eris" );

                mainListView.setAdapter( listAdapter );
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
        List<String> route = dataController.getAllRoutes();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, route);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        s.setAdapter(dataAdapter);
    }

    public ArrayList getRoutes(){
        return new ArrayList(8);
    }
}
