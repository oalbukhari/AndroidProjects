package com.example.fedom.beforeexam.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.fedom.beforeexam.Database.DBHelper;
import com.example.fedom.beforeexam.Database.PersonTable;

/**
 * Created by fedom on 21/02/2017.
 */

public class ContentProviderPerson extends ContentProvider {

    DBHelper dbhepler;

    private static final String AUTHORITY = "com.example.fedom.beforeexam.ContentProvider";
    private static final String BASE_PATH_PERSON="persona";

    public static final Uri URI_PERSON = Uri.parse("content://" + AUTHORITY + "/" +BASE_PATH_PERSON);

    private static final UriMatcher uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);

    private static final String name="/name";
    private static final String surname="/surname";
    private static final String eta="/age";


    private static final int PERSON=1010;
    private static final int PERSON_ID=1020;
    private static final int PERSON_NAME=1030;
    private static final int PERSON_SURNAME=1040;
    private static final int PERSON_ETA=1050;
    static {
        //Uri for making questions about Person
        // content://authority/person
        uriMatcher.addURI(AUTHORITY, BASE_PATH_PERSON,PERSON);
        // content://authority/person/:id
        uriMatcher.addURI(AUTHORITY, BASE_PATH_PERSON + "/#", PERSON_ID);
        //content://authority/person/name/:name
        uriMatcher.addURI(AUTHORITY,BASE_PATH_PERSON + name +"/#",PERSON_NAME);
        //content://authority/person/surname/:surname
        uriMatcher.addURI(AUTHORITY,BASE_PATH_PERSON + surname+"/#",PERSON_SURNAME);
        //content://authority/person/eta/:eta
        uriMatcher.addURI(AUTHORITY,BASE_PATH_PERSON + eta +"/#",PERSON_ETA);

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
        switch (uriType){
            case PERSON:
                queryBuilder.setTables(PersonTable.TABLE_NAME);

                break;
            case PERSON_ID:
                queryBuilder.setTables(PersonTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",PersonTable._ID,uri.getLastPathSegment()));

                break;
            case PERSON_NAME:
                queryBuilder.setTables(PersonTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",PersonTable.NAME,uri.getLastPathSegment()));

                break;
            case PERSON_SURNAME:
                queryBuilder.setTables(PersonTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",PersonTable.SURNAME,uri.getLastPathSegment()));

                break;
            case PERSON_ETA:
                queryBuilder.setTables(PersonTable.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",PersonTable.AGE,uri.getLastPathSegment()));

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
            case PERSON:
                insertedId=sqLiteDatabase.insert(PersonTable.TABLE_NAME,null,values);
                vUri= Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_PERSON+ "/" + insertedId);
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
            case PERSON:
                deletedRows = database.delete(PersonTable.TABLE_NAME, selection, selectionArgs);
                break;
            case PERSON_ID:
                if (TextUtils.isEmpty(selection)) {
                    deletedRows = database.delete(PersonTable.TABLE_NAME,
                            PersonTable._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    deletedRows = database.delete(PersonTable.TABLE_NAME,
                            PersonTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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
            case PERSON:
                updatedRows = database.update(PersonTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case PERSON_ID:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(PersonTable.TABLE_NAME, values,
                            PersonTable._ID + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(PersonTable.TABLE_NAME, values,
                            PersonTable._ID + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }

                break;
            case PERSON_NAME:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(PersonTable.TABLE_NAME, values,
                            PersonTable.NAME + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(PersonTable.TABLE_NAME, values,
                            PersonTable.NAME + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }

                break;
            case PERSON_SURNAME:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(PersonTable.TABLE_NAME, values,
                            PersonTable.SURNAME + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(PersonTable.TABLE_NAME, values,
                            PersonTable.SURNAME + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }

                break;
            case PERSON_ETA:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(PersonTable.TABLE_NAME, values,
                            PersonTable.AGE + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(PersonTable.TABLE_NAME, values,
                            PersonTable.AGE + " = " + uri.getLastPathSegment() + " and " + selection,
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
