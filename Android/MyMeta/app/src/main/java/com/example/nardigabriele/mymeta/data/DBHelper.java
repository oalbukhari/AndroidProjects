package com.example.nardigabriele.mymeta.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nardigabriele on 13/01/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String NAME = "weather.db";
    private final static int VERSION = 1;

    public DBHelper(Context aContext) {

        super(aContext, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(cityTable.CREATE_QUERY);
        sqLiteDatabase.execSQL(tempTable.CREATE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(cityTable.DROP_QUERY);
        sqLiteDatabase.execSQL(tempTable.DROP_QUERY);

    }

}
