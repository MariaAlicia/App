package io.hackathon.hackathon2016;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Sami on 30/04/2016.
 */
@Table
public class Stops extends SugarRecord {
	String stop_id, stop_code, stop_name, stop_desc, stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station, stop_timezone;
	private long id;

	public Stops(){

	}

	public Stops(String[] values){
		this.stop_id = values[0];
		this.stop_code = values[1];
		this.stop_name = values[2];
		this.stop_desc = values[3];
		this.stop_lat = values[4];
		this.stop_lon = values[5];
		this.zone_id = values[6];
		this.stop_url = values[7];
		this.location_type = values[8];
		this.parent_station = values[9];
		this.stop_timezone = values[10];
	}

	public Long getId(){
		return this.id;
	}
}
