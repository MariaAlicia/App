package io.hackathon.hackathon2016.helper;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import io.hackathon.hackathon2016.model.Routes;
import io.hackathon.hackathon2016.model.Stop_Times;
import io.hackathon.hackathon2016.model.Stops;
import io.hackathon.hackathon2016.model.Trips;

/**
 * Created by bob on 30/04/16.
 */
public class DataController {
    public DataController() {
    }

    public void clearTables() {
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
        if (!this.needsToUpdate()) return;
        String next[] = {};
        String[] names = {/*"agency.txt","calendar.txt","calendar_dates.txt","feed_info.txt",*/"routes.txt",
                "stops.txt", "stop_times.txt", "trips.txt"};
        for (int i = 0; i < names.length; i++) {
            try {
                CSVReader reader = new CSVReader(new InputStreamReader(assets.open(names[i])));
                reader.readNext();
                next = reader.readNext();
                int lineNum = 0;
                while (next != null && lineNum < 500) {
                    try {
                        switch (names[i]) {
                            case "agency.txt":
                                Agency ag = new Agency(next);
                                ag.save();
                                break;
                            case "calendar.txt":
                                Calendar_Table cal = new Calendar_Table(next);
                                cal.save();
                                break;
                            case "calendar_dates.txt":
                                Calendar_Dates dates = new Calendar_Dates(next);
                                dates.save();
                                break;
                            case "feed_info.txt":
                                Feed_Info feed = new Feed_Info(next);
                                feed.save();
                                break;
                            case "routes.txt":
                                Routes r = new Routes(next);
                                r.save();
                                break;
                            case "stops.txt":
                                Stops stops = new Stops(next);
                                stops.save();
                                break;
                            case "stop_times.txt":
                                Stop_Times stop_times = new Stop_Times(next);
                                stop_times.save();
                                break;
                            case "trips.txt":
                                Trips trips = new Trips(next);
                                trips.save();
                                break;
                            default:
                                System.out.println("nope :( :( :( :(");
                                System.out.println("i'm sorry i can't do that dave");

                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //nothing skips the row
                    }
                    next = reader.readNext();
                    //lineNum++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> stopRoutesAndTime(String stopId) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        List<Stop_Times> times = Stop_Times.find(Stop_Times.class, "stopid = ? AND (arrivaltime LIKE ? OR arrivaltime LIKE ?) ORDER BY arrivaltime", stopId, hour + "%", (hour + 1) + "%");
        ArrayList<String> data = new ArrayList<>();
        for (Stop_Times s : times) {
            Trips trip = Trips.find(Trips.class, "tripid=?", s.trip_id).get(0);

            List<Routes> routelist = Routes.find(Routes.class, "routeid = ? and routetype = ?", trip.route_id, "3");
            if (routelist.size() == 0) continue;
            Routes route = routelist.get(0);
            data.add(this.time24to12(s.arrival_time) + ", " + route.route_short_name + ", " + route.route_long_name);
        }

        return data;
    }

    public boolean needsToUpdate() {
        if (Routes.find(Routes.class, "").size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<String> getAllRoutes() {
        List<String> routeValues = new ArrayList<>();
        List<Routes> routes = Routes.find(Routes.class, "routetype = ? ORDER BY CAST(routeshortname AS int)", "3");
        for (Routes r : routes) {
            routeValues.add(r.route_short_name + ", " + r.route_long_name);
        }
        return routeValues;
    }

    public List<String> getStops(String route) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String shortName = route.split(",")[0];
        Routes r = Routes.find(Routes.class, "routeshortname = ?", shortName).get(0);
        List<Trips> tripsList = Trips.find(Trips.class, "routeid = ? LIMIT 20", r.route_id);
        List<String> stopData = new ArrayList<>();
        for (Trips t : tripsList) {
            List<Stop_Times> stop_timeList = Stop_Times.find(Stop_Times.class, "tripid = ? AND (arrivaltime LIKE ? OR arrivaltime LIKE ?) ORDER BY arrivaltime", t.trip_id, hour + "%", (hour + 1) + "%");
            for (Stop_Times st : stop_timeList) {
                Stops s = Stops.find(Stops.class, "stopid = ?", st.stop_id).get(0);
                stopData.add(s.stop_desc + " : " + this.time24to12(st.arrival_time));
            }
        }

        return stopData;
    }


    public boolean areThereStops(String stopId) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        List<Stop_Times> times = Stop_Times.find(Stop_Times.class, "stopid = ? AND (arrivaltime LIKE ? OR arrivaltime LIKE ?) LIMIT 1", stopId, hour + "%", (hour + 1) + "%");

        return !times.isEmpty();
    }

    private String time24to12(String time24) {
        Calendar c = Calendar.getInstance();
        int currentHours = c.get(Calendar.HOUR_OF_DAY);
        int currentMinutes = c.get(Calendar.MINUTE);
        String[] time24Array = time24.split(":");
        int hour = Integer.parseInt(time24Array[0]) - currentHours;
        int minute = Integer.parseInt(time24Array[1]) - currentMinutes;
        String time = "+" + (hour == 0 ? "" : hour + "h ") + minute + "m";
        if (minute < 0) {
            hour--;
            if (hour < 0) {
                hour = 0;
                time = "-" + (hour == 0 ? "" : hour + "h ") + Math.abs(minute) + "m";
            } else {
                minute += 60;
                time = "+" + (hour == 0 ? "" : hour + "h ") + minute + "m";
            }

        }
        return time;
    }
}