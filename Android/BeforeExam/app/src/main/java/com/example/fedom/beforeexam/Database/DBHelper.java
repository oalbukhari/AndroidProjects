package com.example.fedom.beforeexam.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fedom on 21/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final static String NAME = "weather.db";
    private final static int VERSION = 1;

    public DBHelper(Context aContext) {

        super(aContext, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PersonTable.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PersonTable.DROP_QUERY);

    }
}
