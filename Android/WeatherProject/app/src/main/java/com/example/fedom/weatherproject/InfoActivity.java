package com.example.fedom.weatherproject;



import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fedom.weatherproject.Adapter.TemperatureCursorAdapter;
import com.example.fedom.weatherproject.ContentProvider.FullContentProvider;
import com.example.fedom.weatherproject.DataBase.CityTable;
import com.example.fedom.weatherproject.DataBase.DBHelper;
import com.example.fedom.weatherproject.DataBase.TemperatureTable;
import com.example.fedom.weatherproject.Dialogs.DialogUpdateTemperature;

public class InfoActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> , DialogUpdateTemperature.IDialogEditListener{

    TemperatureCursorAdapter tempCursorAdapter;

    private static final String CITY_NAME = "City_name";
    long mCityID;
     ListView listTemperature;
    public static final String ITEM_ID=" HEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        listTemperature = (ListView) findViewById(R.id.list_info);


        listTemperature.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUpdateTemperature updateTemperature = DialogUpdateTemperature.getInstance(id);
                updateTemperature.show(getSupportFragmentManager(),"EDIT_TAG");
                return true;
            }
        });
      /*  listTemperature.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vIntent = new Intent(InfoActivity.this, MapsActivity.class);
                Bundle vBundle = new Bundle();
                vBundle.putLong(ITEM_ID, id);
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
            }
        });*/


        Bundle vExtras = getIntent().getExtras();
        mCityID = vExtras.getLong(MainActivity.ITEM_ID);
        // Giusto per visualizzare anche il nome della città
        if (savedInstanceState != null) {
            setTitle(savedInstanceState.getString(CITY_NAME));
        } else {
            SQLiteDatabase vDB = new DBHelper(this).getReadableDatabase();
            Cursor vCursor = vDB.query(CityTable.TABLE_NAME, null, CityTable._ID + "=" + mCityID,null,null,null,null);
            while (vCursor.moveToNext()){
                setTitle(vCursor.getString(vCursor.getColumnIndex(CityTable.NAME)));
            }
            vCursor.close();
            vDB.close();
        }
        tempCursorAdapter = new TemperatureCursorAdapter(this, null);
        listTemperature.setAdapter(tempCursorAdapter);

        getLoaderManager().initLoader(1,null,this);

      //  getLoaderManager().initLoader(0,null,this);




    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("onCreateLoader", "onCreateLoader");
        CursorLoader cursorLoader;
        //  Uri vTemp = Uri.parse(TempContentProvider.TEMP_URI + "/" + getTitle());
        cursorLoader = new CursorLoader(this, FullContentProvider.URI_TEMPERATURE,null,TemperatureTable.CITY_ID_EXTERNAL+" = "+mCityID, null, null);
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

    @Override
    public void updateInfoTemperature(long idPass, EditText Value) {

        Uri uri = Uri.parse(FullContentProvider.URI_TEMPERATURE+"/"+ idPass);
        ContentValues cValues= new ContentValues();
        cValues.put(TemperatureTable.TEMPERATURE, Value.getText().toString());
        getContentResolver().update(uri,cValues,null,null);

    }
}
