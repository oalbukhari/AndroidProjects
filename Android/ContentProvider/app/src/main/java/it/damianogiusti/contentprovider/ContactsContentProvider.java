package it.damianogiusti.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import it.damianogiusti.contentprovider.database.ContactHelper;
import it.damianogiusti.contentprovider.database.DBHelper;

/**
 * Created by Damiano Giusti on 16/12/16.
 */

public class ContactsContentProvider extends ContentProvider {

    //region INITIALIZATION

    // data content types formalization
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/contacts";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/contact";

    // content provider Uri formalization
    private static final String AUTHORITY = "it.damianogiusti.contactscontentprovider";
    private static final String BASE_PATH_CONTACTS = "contacts";

    public static final Uri CONTACTS_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_CONTACTS);

    // UriMatcher configuration
    private static final int CONTACTS = 1010;
    private static final int CONTACTS_ID = 1020;
    private static final UriMatcher uriMatcher = ner.NO_MATCH);

    static {
        // content://authority/contacts
        uriMatcher.addURI(AUTHORITY, BASE_PATH_CONTACTS, CONTACTS);
        // content://authority/contacts/:id
        uriMatcher.addURI(AUTHORITY, BASE_PATH_CONTACTS + "/#", CONTACTS_ID);
    }

    //endregion INITIALIZATION

    //region INSTANCE COMPONENTS

    private DBHelper databaseHelper;

    @Override
    public boolean onCreate() {
        databaseHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check for matching on the given uri
        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case CONTACTS:
                queryBuilder.setTables(ContactHelper.TABLE_NAME);
                break;
            case CONTACTS_ID:
                queryBuilder.setTables(ContactHelper.TABLE_NAME);
                queryBuilder.appendWhere(String.format("%s = %s", ContactHelper._ID, uri.getLastPathSegment()));
                break;
            default:
                throw new IllegalArgumentException("Uri not supported: " + uri);
        }

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
       // database.close();
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        long insertedId = 0;

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case CONTACTS:
                insertedId = database.insert(ContactHelper.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Uri not supported: " + uri);
        }
        database.close();

        // if insertion went successful
        if (insertedId >= 0) {
            // notify listeners on this uri that data changed
            getContext().getContentResolver().notifyChange(uri, null);
            // return the uri of newly inserted item
            return Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_CONTACTS + "/" + insertedId);
        }
        // something went wrong on insertion
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int deletedRows;

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case CONTACTS:
                deletedRows = database.delete(ContactHelper.TABLE_NAME, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                if (TextUtils.isEmpty(selection)) {
                    deletedRows = database.delete(ContactHelper.TABLE_NAME,
                            ContactHelper._ID + " = " + uri.getLastPathSegment(),
                            null);
                } else {
                    deletedRows = database.delete(ContactHelper.TABLE_NAME,
                            ContactHelper._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int updatedRows;

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case CONTACTS:
                updatedRows = database.update(ContactHelper.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                if (TextUtils.isEmpty(selection)) {
                    updatedRows = database.update(ContactHelper.TABLE_NAME, values,
                            ContactHelper._ID + " = " + uri.getLastPathSegment(), null);
                } else {
                    updatedRows = database.update(ContactHelper.TABLE_NAME, values,
                            ContactHelper._ID + " = " + uri.getLastPathSegment() + " and " + selection,
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

    //endregion
}
