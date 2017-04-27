package com.example.fedom.weatherproject.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.fedom.weatherproject.DataBase.CityTable;
import com.example.fedom.weatherproject.DataBase.DBHelper;
import com.example.fedom.weatherproject.DataBase.TemperatureTable;

/**
 * Created by fedom on 24/01/2017.
 */

public class FullContentProvider extends ContentProvider {

    //Variables just in use

    DBHelper dbhepler;



    // Creation Variables for URI

    private static final String AUTHORITY = "com.example.fedom.weatherproject.ContentProvider";
    private static final String BASE_PATH_CITY="city";
    private static final String BASE_PATH_TEMPERATURE="temperature";

    public static final Uri URI_CITY = Uri.parse("content://" + AUTHORITY + "/" +BASE_PATH_CITY);
    public static final Uri URI_TEMPERATURE = Uri.parse("content://" + AUTHORITY + "/" +BASE_PATH_TEMPERATURE);



    private static final UriMatcher uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
    private static final int CITY=1010;
    private static final int CITY_ID=1020;
    private static final int CITY_NAME=1030;
    private static final int SEARCH_NAME=1040;

    private static final int TEMPERATURE=2010;
    private static final int TEMPERATURE_ID=2020;
    private static final int TEMPERATURE_DATA=2040;
    private static final int TEMPERATURE_ID_EXTERNAL=2050;

    static {
        //Uri for making questions about CityTable
            // content://authority/city
            uriMatcher.addURI(AUTHORITY, BASE_PATH_CITY, CITY);
            // content://authority/city/:id
            uriMatcher.addURI(AUTHORITY, BASE_PATH_CITY + "/#", CITY_ID);
            //content://authority/city/namecity/:name
            uriMatcher.addURI(AUTHORITY,BASE_PATH_CITY + "/namecity/#",CITY_NAME);
        //search name:
        uriMatcher.addURI(AUTHORITY,BASE_PATH_CITY+ "/searchname/#",SEARCH_NAME);

        //Uri for making questions about TemperatureTable
            // content://authority/temperature
            uriMatcher.addURI(AUTHORITY,BASE_PATH_TEMPERATURE,TEMPERATURE);
            //contenty://authority/temperature/:id
            uriMatcher.addURI(AUTHORITY,BASE_PATH_TEMPERATURE + "/#" ,TEMPERATURE_ID);
            //content://authority/temperature/registerdate/:date
            uriMatcher.addURI(AUTHORITY,BASE_PATH_TEMPERATURE + "/registerdate/#",TEMPERATURE_DATA);
            //content://authority/temperature/idcity/:temperature_id_external
            uriMatcher.addURI(AUTHORITY,BASE_PATH_TEMPERATURE+"/idcity/#",TEMPERATURE_ID_EXTERNAL);
    }




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
                queryBuilder.setTables(CityTable.TABLE_NAME);
                break;
            case CITY_ID:
                queryBuilder.setTables(CityTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",CityTable._ID,uri.getLastPathSegment()));
                break;
            case CITY_NAME:
                queryBuilder.setTables(CityTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",CityTable.NAME,uri.getLastPathSegment()));
                break;
            case SEARCH_NAME:
                queryBuilder.setTables(CityTable.TABLE_NAME);
                queryBuilder.appendWhere(selection);
                break;
                //queryBuilder.appendWhere(selection);
            case TEMPERATURE:
                queryBuilder.setTables(TemperatureTable.TABLE_NAME);
                break;

            //the selection will take the informations
            case TEMPERATURE_ID:
                queryBuilder.setTables(TemperatureTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",TemperatureTable._ID,uri.getLastPathSegment()));
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
        Uri vUri;


        int uriType=uriMatcher.match(uri);
        switch (uriType){
            case CITY:
                insertedId=sqLiteDatabase.insert(CityTable.TABLE_NAME,null,values);
                vUri= Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_CITY+ "/" + insertedId);
                break;
            case TEMPERATURE:
                insertedId=sqLiteDatabase.insert(TemperatureTable.TABLE_NAME,null,values);
                vUri= Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_TEMPERATURE+ "/" + insertedId);
                break;
            default:
                throw new IllegalArgumentException("Uri not supported"+uri);

        }
        sqLiteDatabase.close();

        getContext().getContentResolver().notifyChange(uri,null);

        if(insertedId>0){

            return vUri;
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
                deletedRows = database.delete(CityTable.TABLE_NAME, selection, selectionArgs);
                break;
            case CITY_ID:
                if (TextUtils.isEmpty(selection)) {
                    deletedRows = database.delete(CityTable.TABLE_NAME,
                            CityTable._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    deletedRows = database.delete(CityTable.TABLE_NAME,
                            CityTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }

                break;
            case TEMPERATURE:
                deletedRows = database.delete(TemperatureTable.TABLE_NAME, selection, selectionArgs);
                break;
            case TEMPERATURE_ID:
                if (TextUtils.isEmpty(selection)) {
                    deletedRows = database.delete(TemperatureTable.TABLE_NAME,
                            TemperatureTable._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    deletedRows = database.delete(TemperatureTable.TABLE_NAME,
                            TemperatureTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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
                updatedRows = database.update(CityTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CITY_ID:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(CityTable.TABLE_NAME, values,
                            CityTable._ID + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(CityTable.TABLE_NAME, values,
                            CityTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }

                break;

            case CITY_NAME:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(CityTable.TABLE_NAME, values,
                            CityTable.NAME+ " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(CityTable.TABLE_NAME, values,
                            CityTable.NAME+ " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }

                break;

            case TEMPERATURE:
                updatedRows = database.update(TemperatureTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case TEMPERATURE_ID:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(TemperatureTable.TABLE_NAME, values,
                            TemperatureTable._ID + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(TemperatureTable.TABLE_NAME, values,
                            TemperatureTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }
                break;

            case TEMPERATURE_DATA:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(TemperatureTable.TABLE_NAME, values,
                            TemperatureTable.DATE + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(TemperatureTable.TABLE_NAME, values,
                            TemperatureTable.DATE + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }
                break;
            case TEMPERATURE_ID_EXTERNAL:
                if(TextUtils.isEmpty(selection)){
                    updatedRows=database.update(TemperatureTable.TABLE_NAME, values,
                            TemperatureTable.CITY_ID_EXTERNAL + "=" + uri.getLastPathSegment(),null);
                } else {
                    updatedRows=database.update(TemperatureTable.TABLE_NAME, values,
                            TemperatureTable.CITY_ID_EXTERNAL+ "=" + uri.getLastPathSegment()+ " and " + selection,selectionArgs);
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
