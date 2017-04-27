package com.example.nardigabriele.mymeta;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.nardigabriele.mymeta.data.DBHelper;
import com.example.nardigabriele.mymeta.data.TempContentProvider;
import com.example.nardigabriele.mymeta.data.TempCursorAdapter;
import com.example.nardigabriele.mymeta.data.tempTable;

public class CityInfo extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    TempCursorAdapter tempCursorAdapter;

    private static final String CITY_NAME = "City_name";
    long mCityID;
    private ListView listTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);
        listTemperature = (ListView) findViewById(R.id.list_info);

        Bundle vExtras = getIntent().getExtras();
        mCityID = vExtras.getLong(MainActivity.ITEM_ID);
        // Giusto per visualizzare anche il nome della città
        if (savedInstanceState != null) {
            setTitle(savedInstanceState.getString(CITY_NAME));
        } else {
            SQLiteDatabase vDB = new DBHelper(this).getReadableDatabase();
            Cursor vCursor = vDB.query(tempTable.TABLE_NAME, null, tempTable._ID + "=" + mCityID, null, null, null, null);
            while (vCursor.moveToNext()) {
                setTitle(vCursor.getString(vCursor.getColumnIndex(tempTable.NAME)));
            }
            vCursor.close();
            vDB.close();
        }
        tempCursorAdapter = new TempCursorAdapter(this, null);
        listTemperature.setAdapter(tempCursorAdapter);

        getLoaderManager().initLoader(1,null,this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("onCreateLoader", "onCreateLoader");
        CursorLoader cursorLoader;
        //  Uri vTemp = Uri.parse(TempContentProvider.TEMP_URI + "/" + getTitle());
        cursorLoader = new CursorLoader(this, TempContentProvider.TEMP_URI,null,tempTable.NAME+"="+getTitle(), null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        tempCursorAdapter.swapCursor(data);
        Log.d("onLoadFinished", "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        tempCursorAdapter.swapCursor(null);
        Log.d("onLoaderReset", "onLoaderReset");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME, getTitle().toString());
    }
}
