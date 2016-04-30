package io.hackathon.hackathon2016;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Sami on 30/04/2016.
 */
@Table
public class Stop_Times extends SugarRecord {
	String trip_id, arrival_time, departure_time, stop_id, stop_sequence, stop_headsign, pickup_type, drop_off_type, shape_dist_travelled;
	private long id;

	public Stop_Times(){

	}

	public Stop_Times(String[] values){
		this.trip_id = values[0];
		this.arrival_time = values[1];
		this.departure_time = values[2];
		this.stop_id = values[3];
		this.stop_sequence = values[4];
		this.stop_headsign = values[5];
		this.pickup_type = values[6];
		this.drop_off_type = values[7];
		this.shape_dist_travelled = values[8];
	}

	public Long getId(){
		return this.id;
	}
}
