package com.example.fedom.maybeprojectwork.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fedom on 19/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String NAME = "project.db";
    private final static int VERSION = 1;

    public DBHelper(Context aContext) {

        super(aContext, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AnagrafeTabella.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AnagrafeTabella.DROP_QUERY);

    }
}
