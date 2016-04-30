package io.hackathon.hackathon2016;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by mgoo on 30/04/16.
 */
@Table
public class Agency extends SugarRecord{
    String agency_id,agency_name,agency_url,agency_timezone,agency_lang,agency_phone,agency_fare_url;
    private long id;

    public Agency(){

    }

    public Agency(String[] values){
        this.agency_id = values[0];
        this.agency_name = values[1];
        this.agency_url = values[2];
        this.agency_timezone = values[3];
        this.agency_lang = values[4];
        this.agency_phone = values[5];
        this.agency_fare_url = values[6];
    }

    public Long getId(){
        return this.id;
    }
}
