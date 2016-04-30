package io.hackathon.hackathon2016;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Sami on 30/04/2016.
 */
@Table
public class Trips extends SugarRecord {
	String route_id, service_id, trip_id, trip_headsign, direction_id, block_id, shape_id, wheelchair_accessible, bikes_allowed;
	private long id;

	public Trips(){

	}

	public Trips(String[] values){
		this.route_id = values[0];
		this.service_id = values[1];
		this.trip_id = values[2];
		this.trip_headsign = values[3];
		this.direction_id = values[4];
		this.block_id = values[5];
		this.shape_id = values[6];
		this.wheelchair_accessible = values[7];
		this.bikes_allowed = values[8];
	}

	public Long getId(){
		return this.id;
	}
}
