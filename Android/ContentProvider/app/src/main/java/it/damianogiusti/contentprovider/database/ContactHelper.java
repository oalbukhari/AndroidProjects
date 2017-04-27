package it.damianogiusti.contentprovider.database;

import android.provider.BaseColumns;

/**
 * Created by Damiano Giusti on 14/12/16.
 */

public class ContactHelper implements BaseColumns {
    public static String TABLE_NAME = "contacts";
    public static String NAME = "name";
    public static String SURNAME = "surname";

    public static String CREATE_QUERY =
            "CREATE TABLE " + TABLE_NAME + "( " +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, " +
                    SURNAME + " TEXT NOT NULL" +
                    ");";
}
