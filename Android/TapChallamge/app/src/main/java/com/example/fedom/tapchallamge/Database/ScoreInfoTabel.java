package com.example.fedom.tapchallamge.Database;

import android.provider.BaseColumns;

/**
 * Created by fedom on 23/02/2017.
 */

public class ScoreInfoTabel implements BaseColumns {


    public static final String TABLE_NAME="Tap";
    public static final String NAME="name";
    public static final String SCORE="score";



    public static final String CREATE_TABLE="CREATE TABLE " +
            TABLE_NAME + " ( "
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME +" TEXT NOT NULL, "
            + SCORE +" TEXT NOT NULL"+ " );";


    public static final String DROP_QUERY = "DROP TABLE IF EXISTS" + TABLE_NAME;
}
