package io.hackathon.hackathon2016;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by mgoo on 30/04/16.
 */
@Table
public class Calendar_Dates extends SugarRecord{
    String service_id,date,exception_type;
    private long id;

    public Calendar_Dates(){

    }

    public Calendar_Dates(String[] values){
        this.service_id = values[0];
        this.date = values[1];
        this.exception_type = values[2];
    }

    public Long getId(){
        return this.id;
    }
}
