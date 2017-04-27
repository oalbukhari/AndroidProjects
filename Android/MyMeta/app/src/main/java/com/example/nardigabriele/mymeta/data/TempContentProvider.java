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

public class TempContentProvider extends ContentProvider {

    //Content provider uri formalization

    private static final String AUTHORITY = "com.example.nardigabriele.mymeta.data.TempContentProvider";
    private static final String BASE_PATH_TEMP="temp";

    public static final Uri TEMP_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_TEMP);


    //Uri Matcher Configuration
    private static final UriMatcher uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
    private static final int TEMP=1010;
    private static final int TEMP_ID=1020;
    private static final int NAME=1030;

    static {
        // content://authority/temp
        uriMatcher.addURI(AUTHORITY, BASE_PATH_TEMP, TEMP);
        // content://authority/temp/:id
        uriMatcher.addURI(AUTHORITY, BASE_PATH_TEMP + "/#", TEMP_ID);

        uriMatcher.addURI(AUTHORITY,BASE_PATH_TEMP+"/temperature/#",NAME);
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
            case TEMP:
                queryBuilder.setTables(tempTable.TABLE_NAME);
                if(!selection.isEmpty()){
                    queryBuilder.appendWhere(selection);
                }
                break;
            case TEMP_ID:
                queryBuilder.setTables(tempTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s=%s",tempTable._ID,uri.getLastPathSegment()));
                break;
            case NAME:
                queryBuilder.setTables(tempTable.TABLE_NAME);
              //  queryBuilder.appendWhere(String.format("%s=%s", tempTable.NAME,uri.getLastPathSegment()));
                queryBuilder.appendWhere(selection);
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
            case TEMP:
                insertedId=sqLiteDatabase.insert(tempTable.TABLE_NAME,null,values);
                break;
            default:
                throw new IllegalArgumentException("Uri not supported"+uri);

        }
        sqLiteDatabase.close();
        if(insertedId>=0){
            getContext().getContentResolver().notifyChange(uri,null);
            return Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_TEMP + "/" + insertedId);
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {


        SQLiteDatabase database = dbhepler.getWritableDatabase();
        int deletedRows;

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case TEMP:
                deletedRows = database.delete(tempTable.TABLE_NAME, selection, selectionArgs);
                break;
            case TEMP_ID:
                if (TextUtils.isEmpty(selection)) {
                    deletedRows = database.delete(tempTable.TABLE_NAME,
                            tempTable._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    deletedRows = database.delete(tempTable.TABLE_NAME,
                            tempTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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
            case TEMP:
                updatedRows = database.update(tempTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case TEMP_ID:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(tempTable.TABLE_NAME, values,
                            tempTable._ID + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(tempTable.TABLE_NAME, values,
                            tempTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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
