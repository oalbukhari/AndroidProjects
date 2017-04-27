package com.example.fedom.weatherproject.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fedom on 24/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String NAME = "weather.db";
    private final static int VERSION = 1;

    public DBHelper(Context aContext) {

        super(aContext, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CityTable.CREATE_QUERY);
        db.execSQL(TemperatureTable.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CityTable.DROP_QUERY);
        db.execSQL(TemperatureTable.DROP_QUERY);
    }
}
