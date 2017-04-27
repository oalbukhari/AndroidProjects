package com.example.fedom.maybeprojectwork.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.fedom.maybeprojectwork.Database.AnagrafeTabella;
import com.example.fedom.maybeprojectwork.Database.DBHelper;

/**
 * Created by fedom on 19/01/2017.
 */

public class AnagrafeContentProvider extends ContentProvider{
    private static final String AUTHORITY = "com.example.fedom.maybeprojectwork.ContentProvider";
    private static final String BASE_PATH_ANAGRAFE="anagrafe";

    public static final Uri ANAGRAFE_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_ANAGRAFE);


    //Uri Matcher Configuration
    private static final UriMatcher uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
    private static final int ANAGRAFE=1010;
    private static final int ANAGRAFE_ID=1020;

    static {
        // content://authority/anagrafe
        uriMatcher.addURI(AUTHORITY, BASE_PATH_ANAGRAFE, ANAGRAFE);
        // content://authority/anagrafe/:id
        uriMatcher.addURI(AUTHORITY, BASE_PATH_ANAGRAFE+ "/#", ANAGRAFE_ID);
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
            case ANAGRAFE:
                queryBuilder.setTables(AnagrafeTabella.TABLE_NAME);
                break;
            case ANAGRAFE_ID:
                queryBuilder.setTables(AnagrafeTabella.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s=%s",AnagrafeTabella._ID,uri.getLastPathSegment()));
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
            case ANAGRAFE:
                insertedId=sqLiteDatabase.insert(AnagrafeTabella.TABLE_NAME,null,values);
                break;
            default:
                throw new IllegalArgumentException("Uri not supported"+uri);

        }

        sqLiteDatabase.close();
        getContext().getContentResolver().notifyChange(uri,null);
        if(insertedId>0){
            return Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_ANAGRAFE + "/" + insertedId);
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbhepler.getWritableDatabase();
        int deletedRows;

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case ANAGRAFE:
                deletedRows = database.delete(AnagrafeTabella.TABLE_NAME, selection, selectionArgs);
                break;
            case ANAGRAFE_ID:
                if (TextUtils.isEmpty(selection)) {
                    deletedRows = database.delete(AnagrafeTabella.TABLE_NAME,
                            AnagrafeTabella._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    deletedRows = database.delete(AnagrafeTabella.TABLE_NAME,
                            AnagrafeTabella._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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
            case ANAGRAFE:
                updatedRows = database.update(AnagrafeTabella.TABLE_NAME, values, selection, selectionArgs);
                break;
            case ANAGRAFE_ID:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(AnagrafeTabella.TABLE_NAME, values,
                            AnagrafeTabella._ID + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(AnagrafeTabella.TABLE_NAME, values,
                            AnagrafeTabella._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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
