package com.ab.smartwallet;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Abdullah on 3/12/2016.
 */
public class DataBase extends SugarRecord {
    String date ;
    String category ;
    String amount ;
    String plusOrMinus ;

    public DataBase () {

    }

public DataBase (String date, String category,String amount,String plusOrMinus){
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.plusOrMinus = plusOrMinus;

    }
}
