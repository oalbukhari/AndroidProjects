package com.example.fedom.weatherproject;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fedom.weatherproject.Adapter.CityCursorAdapter;
import com.example.fedom.weatherproject.ContentProvider.FullContentProvider;
import com.example.fedom.weatherproject.DataBase.CityTable;
import com.example.fedom.weatherproject.DataBase.DBHelper;
import com.example.fedom.weatherproject.DataBase.TemperatureTable;
import com.example.fedom.weatherproject.Dialogs.DialogChosen;
import com.example.fedom.weatherproject.Dialogs.DialogDelete;
import com.example.fedom.weatherproject.Dialogs.DialogInsertInfo;
import com.example.fedom.weatherproject.Dialogs.DialogUpdateCity;


public class MainActivity extends AppCompatActivity implements
                                                                DialogChosen.IDialogChosenListener ,
                                                                DialogDelete.IDialogDeleteListener,
                                                                DialogUpdateCity.IDialogEditListener,
                                                                DialogInsertInfo.IDialogGeneralListener,
                                                                LoaderManager.LoaderCallbacks<Cursor>{
    //Variables
    private Long iDentification_controller;
    public static final String ITEM_ID="ITEM_ID";
    private CityCursorAdapter cityAdapter;
    //TAG
    public static final String TAG_DIALOG_INSERT="tagdialoginsert";
    public static final String TAG_DIALOG_CHOSEN="tagdialogchosen";




// implementations of all things in the main of the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("Lista Città");
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        ListView vList = (ListView) findViewById(R.id.list_view_city);

        ActionBarContainer actionBarContainer= (ActionBarContainer)findViewById(R.id.addCity);
        actionBarContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInsertInfo dialogGeneral = new DialogInsertInfo();
                dialogGeneral.show(getSupportFragmentManager(), TAG_DIALOG_INSERT);

            }
        });


        cityAdapter = new CityCursorAdapter(this, null);
        vList.setAdapter(cityAdapter);

        getLoaderManager().initLoader(0, null, this);

       /* Button vBtn = (Button) findViewById(R.id.addCityAndInfo);
        vBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInsertInfo dialogGeneral = new DialogInsertInfo();
                dialogGeneral.show(getSupportFragmentManager(), TAG_DIALOG_INSERT);

            }
        });*/
        vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vIntent = new Intent(MainActivity.this, InfoActivity.class);
                Bundle vBundle = new Bundle();
                vBundle.putLong(ITEM_ID, id);
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
            }
        });


        vList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DialogChosen dialogChosen = DialogChosen.getInstance(id);
                dialogChosen.show(getSupportFragmentManager(),TAG_DIALOG_CHOSEN);

                return true;
            }

        });




    }


    //more functions




// ovverriding methodes that I have created before



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader =
                new CursorLoader(this, FullContentProvider.URI_CITY, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cityAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void deleteInDb(long aItemid) {

        Uri uriToDelete = Uri.parse(FullContentProvider.URI_CITY + "/" + aItemid);

        getContentResolver().delete(uriToDelete, null, null);
        uriToDelete=Uri.parse(FullContentProvider.URI_TEMPERATURE+"/"+aItemid);
        getContentResolver().delete(uriToDelete,null,null);


    }

    @Override
    public void DeleteOrEdit(Boolean choose, long vId) {
        if(choose){
            DialogUpdateCity dialogEditCity = DialogUpdateCity.getInstance(vId);
            dialogEditCity.show(getSupportFragmentManager(),"EDIT_TAG");
        }else{
            DialogDelete dialogDelete= DialogDelete.getInstance(vId);
            dialogDelete.show(getSupportFragmentManager(),"TAG_DELETE");
        }

    }



    @Override
    public void updateInfoCity(long idPass, EditText Value) {

        SQLiteDatabase checkdb = new DBHelper(this).getReadableDatabase();
        Cursor vcursor;
      try {
            vcursor = checkdb.query(CityTable.TABLE_NAME, null, CityTable.NAME + "= '" + Value.getText().toString() + "'", null, null, null, null);
            if (vcursor.moveToFirst()) {
                iDentification_controller = vcursor.getLong(vcursor.getColumnIndex(CityTable._ID));
            }
        } catch (SQLiteException e) {
            Log.d("errore", "" + e);
        }
        checkdb.close();
        Uri uri_city = Uri.parse(FullContentProvider.URI_CITY + "/" + idPass);
        if(iDentification_controller==null) {

            ContentValues cValues = new ContentValues();
            cValues.put(CityTable.NAME, Value.getText().toString());
            getContentResolver().update(uri_city, cValues, null, null);

       }else{
            Uri uri_temperature = Uri.parse(FullContentProvider.URI_TEMPERATURE + "/idcity/" + idPass);
            ContentValues temperatureValue= new ContentValues();
            temperatureValue.put(TemperatureTable.CITY_ID_EXTERNAL,iDentification_controller);
            int updatedRowsCount = getContentResolver().update(uri_temperature, temperatureValue, null, null);
            Log.d("updateInfoCity", "updateInfoCity: " + updatedRowsCount);
            int deletedRows = getContentResolver().delete(uri_city, CityTable._ID + " = " + idPass, null);
            Log.d("deleteInfoCity", "deleteInfoCity: " + deletedRows);
        }


    }



    @Override
    public void insertINFO(CityAndTemp info) {
        SQLiteDatabase checkDB= new DBHelper(this).getReadableDatabase();
        try {
            Cursor vCursor = checkDB.query(CityTable.TABLE_NAME,null, CityTable.NAME + " = '"+info.getNomeCitta()+"'",null,null,null,null);
            if (vCursor.moveToFirst()){
                iDentification_controller=vCursor.getLong(vCursor.getColumnIndex(CityTable._ID));
            }
        }catch (SQLiteException e){

        }
        if (iDentification_controller!=null){
            ContentValues vValuesTemp = new ContentValues();
            vValuesTemp.put(TemperatureTable.CITY_ID_EXTERNAL, iDentification_controller);
            vValuesTemp.put(TemperatureTable.DATE, info.getDataInseriemnto());
            vValuesTemp.put(TemperatureTable.TEMPERATURE, info.getTemperatura());
            getContentResolver().insert(FullContentProvider.URI_TEMPERATURE, vValuesTemp);
        }else {

            ContentValues vValuesCity = new ContentValues();
            vValuesCity.put(CityTable.NAME, info.getNomeCitta());
            Uri uri = getContentResolver().insert(FullContentProvider.URI_CITY, vValuesCity);


            //insert  temperature info in Temperature tables
            ContentValues vValuesTemp = new ContentValues();
            vValuesTemp.put(TemperatureTable.CITY_ID_EXTERNAL, ContentUris.parseId(uri));
            vValuesTemp.put(TemperatureTable.DATE, info.getDataInseriemnto());
            vValuesTemp.put(TemperatureTable.TEMPERATURE, info.getTemperatura());
            getContentResolver().insert(FullContentProvider.URI_TEMPERATURE, vValuesTemp);

        }

        iDentification_controller=null;
    }

    @Override
    public void searchCity(String insert) {
        insert.toUpperCase();
        if (insert.length()==0){

        }else{
            SQLiteDatabase checkDB = new DBHelper(this).getReadableDatabase();
            Cursor vCursor = checkDB.query(CityTable.TABLE_NAME,null,CityTable.NAME+ " LIKE '"+insert+"%'",null,null,null,null);

        }




    }
}
