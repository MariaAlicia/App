package io.hackathon.hackathon2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import au.com.bytecode.opencsv.CSVReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.updateDatabase();
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
