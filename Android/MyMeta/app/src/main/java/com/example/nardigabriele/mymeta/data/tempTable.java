package com.example.nardigabriele.mymeta.data;

import android.provider.BaseColumns;

/**
 * Created by FEDOM on 13/01/17.
 */

public class tempTable implements BaseColumns {

    public static final String TABLE_NAME = "Temperature";
    public static final String TEMP = "temperatura";
    public static final String DATE = "data";
    public static final String NAME = "citta";


    public static final String CREATE_QUERY = "CREATE TABLE " +
            TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEMP + " REAL NOT NULL ," +
            DATE + " INTEGER NOT NILL ," +
            NAME + " TEXT NOT NULL" +
            ");";

    public static final String DROP_QUERY = "DROP TABLE IF EXISTS" + TABLE_NAME;
}
