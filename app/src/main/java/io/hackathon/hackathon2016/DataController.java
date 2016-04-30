package io.hackathon.hackathon2016;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by bob on 30/04/16.
 */
public class DataController {
    public DataController(){}

    public void clearTables()
    {
        Agency.deleteAll(Agency.class);
        Calendar_Dates.deleteAll(Agency.class);
        Calendar_Table.deleteAll(Calendar_Table.class);
        Feed_Info.deleteAll(Feed_Info.class);
        Routes.deleteAll(Routes.class);
        Shapes.deleteAll(Shapes.class);
        Stop_Times.deleteAll(Stop_Times.class);
        Stops.deleteAll(Stops.class);
        Trips.deleteAll(Trips.class);
    }

    public void updateDatabase(AssetManager assets) {
        //clearTables();//FOR TESTING ONLY
        if (!this.needsToUpdate())return;
        String next[]={};
        String[] names={/*"agency.txt","calendar.txt","calendar_dates.txt","feed_info.txt",*/"routes.txt",
                "stops.txt","stop_times.txt","trips.txt"};
        for (int i =0;i<names.length;i++) {
            try {
                CSVReader reader = new CSVReader(new InputStreamReader(assets.open(names[i])));
                reader.readNext();
                next=reader.readNext();
                int lineNum = 0;
                while(next != null && lineNum < 500){
                    try {
                        switch (names[i]){
                            case "agency.txt" :
                                Agency ag = new Agency(next);
                                ag.save();
                                break;
                            case "calendar.txt" :
                                Calendar_Table cal = new Calendar_Table(next);
                                cal.save();
                                break;
                            case "calendar_dates.txt" :
                                Calendar_Dates dates =new Calendar_Dates(next);
                                dates.save();
                                break;
                            case "feed_info.txt" :
                                Feed_Info feed = new Feed_Info(next);
                                feed.save();
                                break;
                            case "routes.txt" :
                                Routes r = new Routes(next);
                                r.save();
                                break;
                            case "stops.txt" :
                                Stops stops = new Stops(next);
                                stops.save();
                                break;
                            case "stop_times.txt" :
                                Stop_Times stop_times = new Stop_Times(next);
                                stop_times.save();
                                break;
                            case "trips.txt" :
                                Trips trips = new Trips(next);
                                trips.save();
                                break;
                            default:
                                System.out.println("nope :( :( :( :(");
                                System.out.println("i'm sorry i can't do that dave");

                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        //nothing
                    }

                    next = reader.readNext();
                    lineNum++;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public String[] stopRoutesAndTime(String stopId){
       List<Stop_Times> times=Stop_Times.find(Stop_Times.class,"stopid=?",stopId);
        List<String> stopTimes = new ArrayList<>();
        //String tripId="";
        ArrayList<String> data = new ArrayList<>();
        Routes routes ;
        for (Stop_Times s: times){
            //idTrip.add(s.trip_id);
            stopTimes.add(s.arrival_time);
            Trips trip = Trips.find(Trips.class,"tripid=?",s.trip_id).get(0);
            routes=Routes.find(Routes.class,"routeid=?",trip.route_id).get(0);
            data.add(s.arrival_time+" "+routes.route_short_name+" "+routes.route_long_name);
        }
        String[] dataArray = new String[data.size()];
        for (int i =0 ; i<dataArray.length;i++){
            dataArray[i]=data.get(i);
        }

        return dataArray;


    }
    public boolean needsToUpdate(){
        if (Agency.find(Agency.class,"").size()>0) {
            return false;
        } else {
            return true;
        }
    }

    public List<String> getAllRoutes(){
        List<String> routeValues = new ArrayList<>();
        List<Routes> routes = Routes.find(Routes.class, "routetype = ? ORDER BY CAST(routeshortname AS int)", "3");
        for (Routes r : routes){
            routeValues.add(r.route_short_name + " " + r.route_long_name);
        }
        return routeValues;
    }

}
