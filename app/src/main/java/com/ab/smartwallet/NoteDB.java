package com.ab.smartwallet;

import com.orm.SugarRecord;

/**
 * Created by Abdullah on 3/14/2016.
 */
public class NoteDB extends SugarRecord {
    String note ;
    String title;
    int color;
    String date;
    public NoteDB(){

    }

    public NoteDB(String note,String title,int color,String date){
        this.note = note;
        this.title = title;
        this.color =  color;
        this.date = date;
    }
}
