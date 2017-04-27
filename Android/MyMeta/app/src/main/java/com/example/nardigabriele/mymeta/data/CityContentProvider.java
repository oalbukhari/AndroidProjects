package com.example.nardigabriele.mymeta.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by fedom on 17/01/2017.
 */

public class CityContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.nardigabriele.mymeta.data.CityContentProvider";
    private static final String BASE_PATH_CITY="city";

    public static final Uri CITY_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_CITY);


    //Uri Matcher Configuration
    private static final UriMatcher uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
    private static final int CITY=1010;
    private static final int CITY_ID=1020;

    static {
        // content://authority/city
        uriMatcher.addURI(AUTHORITY, BASE_PATH_CITY, CITY);
        // content://authority/city/:id
        uriMatcher.addURI(AUTHORITY, BASE_PATH_CITY + "/#", CITY_ID);

    }
    private DBHelper dbhepler;
    @Override
    public boolean onCreate() {
        dbhepler = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        int uriType= uriMatcher.match(uri);
        switch(uriType){
            case CITY:
                queryBuilder.setTables(cityTable.TABLE_NAME);
                break;
            case CITY_ID:
                queryBuilder.setTables(cityTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",cityTable._ID,uri.getLastPathSegment()));
                break;
            default:
                throw new IllegalArgumentException("Uri not supported"+uri);

        }

        SQLiteDatabase sqLiteDatabase = dbhepler.getReadableDatabase();
        Cursor cursor = queryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);



        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase sqLiteDatabase= dbhepler.getWritableDatabase();
        long insertedId=0;


        int uriType=uriMatcher.match(uri);
        switch (uriType){
            case CITY:
                insertedId=sqLiteDatabase.insert(cityTable.TABLE_NAME,null,values);
                break;
            default:
                throw new IllegalArgumentException("Uri not supported"+uri);

        }
        sqLiteDatabase.close();
        if(insertedId>=0){
            getContext().getContentResolver().notifyChange(uri,null);
            return Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_CITY+ "/" + insertedId);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbhepler.getWritableDatabase();
        int deletedRows;

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case CITY:
                deletedRows = database.delete(cityTable.TABLE_NAME, selection, selectionArgs);
                break;
            case CITY_ID:
                if (TextUtils.isEmpty(selection)) {
                    deletedRows = database.delete(cityTable.TABLE_NAME,
                            cityTable._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    deletedRows = database.delete(cityTable.TABLE_NAME,
                            cityTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }

                break;
            default:

                throw new IllegalArgumentException("Uri not supported: " + uri);
        }
        database.close();

        if (deletedRows > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return deletedRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbhepler.getWritableDatabase();
        int updatedRows;

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case CITY:
                updatedRows = database.update(cityTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CITY_ID:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(cityTable.TABLE_NAME, values,
                            cityTable._ID + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(cityTable.TABLE_NAME, values,
                            cityTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }

                break;
            default:
                throw new IllegalArgumentException("Uri not supported: " + uri);
        }

        if (updatedRows > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return updatedRows;
    }
}
