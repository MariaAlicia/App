package io.hackathon.hackathon2016.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by mgoo on 30/04/16.
 */
@Table
public class Routes extends SugarRecord {
    String route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color;
    private long id;

    public Routes(){

    }

    public Routes(String[] values){
        this.route_id = values[0];
        this.agency_id = values[1];
        this.route_short_name = values[2];
        this.route_long_name = values[3];
        this.route_desc = values[4];
        this.route_type = values[5];
        this.route_url = values[6];
        this.route_color = values[7];
        this.route_text_color = values[8];
    }

    public Long getId(){
        return this.id;
    }
}
