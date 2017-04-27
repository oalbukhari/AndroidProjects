package com.example.fedom.weatherproject.DataBase;

import android.provider.BaseColumns;

/**
 * Created by fedom on 24/01/2017.
 */

public class TemperatureTable implements BaseColumns {
    public static final String TABLE_NAME = "Temperature";
    public static final String TEMPERATURE = "Temperature";
    public static final String DATE = "Data";
    public static final String CITY_ID_EXTERNAL = "City";


    public static final String CREATE_QUERY = "CREATE TABLE " +
            TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEMPERATURE + " REAL NOT NULL ," +
            DATE + " TEXT NOT NULL ," +
            CITY_ID_EXTERNAL + " INTEGER NOT NULL" +
            ");";

    public static final String DROP_QUERY = "DROP TABLE IF EXISTS" + TABLE_NAME;
}
