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
import android.widget.Spinner;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"YOUR MESSAGE",Toast.LENGTH_LONG).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"YOUR MESSAGE",Toast.LENGTH_LONG).show();
            }
        });

        // Spinner element
        final Spinner s = (Spinner) findViewById(R.id.spinner);

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
        this.updateDatabase();
    }

    public ArrayList getRoutes(){
        return new ArrayList(8);
    }

    public void updateDatabase() {
        if (!this.needsToUpdate())return;
        String next[]={};
        String[] names={"agency.txt","calendar.txt","calendar_dates.txt","feed_info.txt","routes.txt",
                "shapes.txt","stops.txt","stop_times.txt","trips.txt"};
        for (int i =0;i<names.length;i++) {
            try {
                CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open(names[i])));
                next=reader.readNext();
                while(next !=null){
                    switch (names[i]){
                        case "agency.txt" :
                            Agency ag = new Agency(next);
                            ag.save();
                        case "calendar.txt" :
                            Calendar_Table cal = new Calendar_Table(next);
                            cal.save();
                        case "calendar_dates.txt" :
                            Calendar_Dates dates =new Calendar_Dates(next);
                            dates.save();
                        case "feed_info.txt" :
                            Feed_Info feed = new Feed_Info(next);
                            feed.save();
                        case "routes.txt" :
                            Routes r = new Routes(next);
                            r.save();
                        case "shapes.txt" :
                            Shapes s = new Shapes(next);
                            s.save();
                        case "stops.txt" :
                            Stops stops = new Stops(next);
                            stops.save();
                        case "stop_times.txt" :
                            Stop_Times stop_times = new Stop_Times(next);
                            stop_times.save();
                        case "trips.txt" :
                            Trips trips = new Trips(next);
                            trips.save();
                        default:
                            System.out.println("nope :( :( :( :(");
                            System.out.println("i'm sorry i can't do that dave");

                    }

                    next = reader.readNext();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean needsToUpdate(){
        if (Agency.find(Agency.class,"").size()>0) {
            return false;
        } else {
            return true;
        }
    }
}
