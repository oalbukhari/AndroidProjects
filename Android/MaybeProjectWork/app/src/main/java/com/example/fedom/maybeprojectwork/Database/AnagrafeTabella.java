package com.example.fedom.maybeprojectwork.Database;

import android.provider.BaseColumns;

/**
 * Created by fedom on 19/01/2017.
 */

public class AnagrafeTabella implements BaseColumns {

    public static final String TABLE_NAME= "ANAGRAFICHE";
    public static final String SURNAME="COGNOME";
    public static final String NAME= "NOME";
    public static final String BORN_DATE= "DATANASCITA";
    public static final String EMAIL= "EMAIL";
    public static final String TELEPHONE= "TELEFONO";
    public static final String STREET= "VIA";
    public static final String CITY= "CITTA";
    public static final String CAP= "CAP";
    public static final String PROVINCE= "PROVINCIA";
    public static final String LAT= "LAT";
    public static final String LONG= "LONG";



    public static final String CREATE_QUERY=
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SURNAME +  " TEXT NOT NULL, " +
                    NAME+" TEXT NOT NULL, " +
                    BORN_DATE + " TEXT DEFAULT CURRENT_TIMESTAMP, "+
                    EMAIL+ " TEXT NOT NULL, " +
                    TELEPHONE + " TEXT NOT NULL, " +
                    STREET + " TEXT NOT NULL, " +
                    CITY + " TEXT NOT NULL, " +
                    CAP +" TEXT NOT NULL, "+
                    PROVINCE + " TEXT NOT NULL, "+
                    LAT+" REAL , " +
                    LONG +" REAL  "+ ");";

    public static final String DROP_QUERY = "DROP TABLE IF EXISTS" + TABLE_NAME;
}





