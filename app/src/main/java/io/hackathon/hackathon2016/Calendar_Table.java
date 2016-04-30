package io.hackathon.hackathon2016;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by mgoo on 30/04/16.
 */
@Table
public class Calendar_Table extends SugarRecord{
    String service_id,monday,tuesday,wednesday,thursday,friday,saturday,sunday,start_date,end_date;
    private long id;

    public Calendar_Table(){

    }

    public Calendar_Table(String[] values){
        this.service_id = values[0];
        this.monday = values[1];
        this.tuesday = values[2];
        this.wednesday = values[3];
        this.thursday = values[4];
        this.friday = values[5];
        this.saturday = values[6];
        this.sunday = values[7];
        this.start_date = values[8];
        this.end_date = values[9];
    }

    public Long getId(){
        return this.id;
    }
}
