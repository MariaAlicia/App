package io.hackathon.hackathon2016;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Sami on 30/04/2016.
 */
@Table
public class Shapes extends SugarRecord {
	String shape_id, shape_pt_lat, shape_pt_lon, shape_pt_sequence, shape_dist_traveled;
	private long id;
	
	public Shapes(){
		
	}
	
	public Shapes(String[] values){
		this.shape_id = values[0];
		this.shape_pt_lat = values[1];
		this.shape_pt_lon = values[2];
		this.shape_pt_sequence = values[3];
		this.shape_dist_traveled = values[4];
	}
	
	public Long getId(){
		return this.id;
	}
}
