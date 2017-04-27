package com.example.fedom.weatherproject.DataBase;

import android.provider.BaseColumns;

/**
 * Created by fedom on 24/01/2017.
 */

public class CityTable implements BaseColumns {

    public static final String TABLE_NAME="City";
    public static final String NAME="name";


    public static final String CREATE_QUERY= "CREATE TABLE " +
            TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME +" TEXT NOT NULL );";

    public static final String DROP_QUERY = "DROP TABLE IF EXISTS" + TABLE_NAME;
}
