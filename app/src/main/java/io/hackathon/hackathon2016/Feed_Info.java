package io.hackathon.hackathon2016;

import com.google.common.base.Strings;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by mgoo on 30/04/16.
 */
@Table
public class Feed_Info extends SugarRecord {
    String feed_publisher_name,feed_publisher_url,feed_lang,feed_start_date,feed_end_date,feed_version;
    private long id;

    public Feed_Info(){

    }

    public Feed_Info(String[] values){
        this.feed_publisher_name = values[0];
        this.feed_publisher_url = values[1];
        this.feed_lang = values[2];
        this.feed_start_date = values[3];
        this.feed_end_date = values[4];
        this.feed_version = values[5];
    }

    public Long getId(){
        return this.id;
    }

}
