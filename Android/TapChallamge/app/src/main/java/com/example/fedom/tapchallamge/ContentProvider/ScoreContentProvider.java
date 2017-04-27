package com.example.fedom.tapchallamge.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.fedom.tapchallamge.Database.DBHelper;
import com.example.fedom.tapchallamge.Database.ScoreInfoTabel;

/**
 * Created by fedom on 23/02/2017.
 */

public class ScoreContentProvider extends ContentProvider {
    //implement some variables

    DBHelper dbHelper;


    //Creation URI
    public static final String AUTHORITY ="com.example.fedom.tapchallamge.ContentProvider";
    public static final String BASE_PATH_SCORE="score";

    public static final Uri URI_SCORE=Uri.parse("content://"+AUTHORITY+"/"+BASE_PATH_SCORE);

    private static final UriMatcher URI_MATCHER= new UriMatcher(UriMatcher.NO_MATCH);

    private static final String scorename="scorename";
    private static final String scorenumber="scorenumber";

    private static final int SCORE=10;
    private static final int SCORE_ID=20;
    private static final int SCORE_NAME=30;
    private static final int SCORE_NUMBER=40;

    static {
        URI_MATCHER.addURI(AUTHORITY,BASE_PATH_SCORE,SCORE);
        URI_MATCHER.addURI(AUTHORITY,BASE_PATH_SCORE+"/#",SCORE_ID);
        URI_MATCHER.addURI(AUTHORITY,BASE_PATH_SCORE+"/"+scorename+"/#",SCORE_NAME);
        URI_MATCHER.addURI(AUTHORITY,BASE_PATH_SCORE+"/"+scorenumber+"/#",SCORE_NUMBER);
    }



    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        int uriType= URI_MATCHER.match(uri);
        switch(uriType){
            case SCORE:
                queryBuilder.setTables(ScoreInfoTabel.TABLE_NAME);
                break;
            case SCORE_ID:
                queryBuilder.setTables(ScoreInfoTabel.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",ScoreInfoTabel._ID,uri.getLastPathSegment()));
                break;
            case SCORE_NAME:
                queryBuilder.setTables(ScoreInfoTabel.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",ScoreInfoTabel.NAME,uri.getLastPathSegment()));
                break;
            case SCORE_NUMBER:
                queryBuilder.setTables(ScoreInfoTabel.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s",ScoreInfoTabel.SCORE,uri.getLastPathSegment()));
                break;
            default:
                throw new IllegalArgumentException("Uri not supported"+uri);

        }

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
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
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        long insertedId=0;
        Uri uril = null;
        int uriType=URI_MATCHER.match(uri);
        switch (uriType) {
            case SCORE:
                insertedId = sqLiteDatabase.insert(ScoreInfoTabel.TABLE_NAME, null, values);
                uril = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_SCORE + "/" + insertedId);
                break;
        }
        sqLiteDatabase.close();
        getContext().getContentResolver().notifyChange(uri,null);
        if(insertedId>0) {
            return uril;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int deletedRows;

        int uriType = URI_MATCHER.match(uri);
        switch (uriType) {
            case SCORE:
                deletedRows = database.delete(ScoreInfoTabel.TABLE_NAME, selection, selectionArgs);
                break;
            case SCORE_ID:
                if (TextUtils.isEmpty(selection)) {
                    deletedRows = database.delete(ScoreInfoTabel.TABLE_NAME,
                            ScoreInfoTabel._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    deletedRows = database.delete(ScoreInfoTabel.TABLE_NAME,
                            ScoreInfoTabel._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int updateRows;

        int uriType = URI_MATCHER.match(uri);
        switch (uriType) {
            case SCORE:
                updateRows = database.delete(ScoreInfoTabel.TABLE_NAME, selection, selectionArgs);
                break;
            case SCORE_ID:
                if (TextUtils.isEmpty(selection)) {
                    updateRows = database.delete(ScoreInfoTabel.TABLE_NAME,
                            ScoreInfoTabel._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    updateRows = database.delete(ScoreInfoTabel.TABLE_NAME,
                            ScoreInfoTabel._ID + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }
                break;
            case SCORE_NAME:
                if (TextUtils.isEmpty(selection)) {
                    updateRows = database.delete(ScoreInfoTabel.TABLE_NAME,
                            ScoreInfoTabel.NAME + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    updateRows = database.delete(ScoreInfoTabel.TABLE_NAME,
                            ScoreInfoTabel.NAME + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }
                break;
            case SCORE_NUMBER:
                if (TextUtils.isEmpty(selection)) {
                    updateRows = database.delete(ScoreInfoTabel.TABLE_NAME,
                            ScoreInfoTabel.SCORE + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    updateRows = database.delete(ScoreInfoTabel.TABLE_NAME,
                            ScoreInfoTabel.SCORE + " = " + uri.getLastPathSegment() + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Uri not supported: " + uri);
        }

        if (updateRows > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return updateRows;


    }
}
