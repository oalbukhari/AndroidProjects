package com.example.fedom.beforeexam.Database;

import android.provider.BaseColumns;

/**
 * Created by fedom on 21/02/2017.
 */

public class PersonTable implements BaseColumns {

    public static final String TABLE_NAME="PERONA";
    public static final String NAME = "NOME";
    public static final String AGE ="ETA";
    public static final String SURNAME="COGNOME";

    public static final String CREATE_QUERY= "CREATE TABLE " +
            TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME +" TEXT NOT NULL, " +
            SURNAME +" TEXT NOT NULL, " +
            AGE +" INTEGER NOT NULL"+");";


    public static final String DROP_QUERY = "DROP TABLE IF EXISTS" + TABLE_NAME;

}
