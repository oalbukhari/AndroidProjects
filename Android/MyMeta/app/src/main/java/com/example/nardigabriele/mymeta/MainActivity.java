package com.example.nardigabriele.mymeta;


import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nardigabriele.mymeta.data.CityContentProvider;
import com.example.nardigabriele.mymeta.data.CityCursorAdapter;
import com.example.nardigabriele.mymeta.data.TempContentProvider;
import com.example.nardigabriele.mymeta.data.cityTable;
import com.example.nardigabriele.mymeta.data.tempTable;
import com.example.nardigabriele.mymeta.dialog.DialogChoose;
import com.example.nardigabriele.mymeta.dialog.DialogDelete;
import com.example.nardigabriele.mymeta.dialog.DialogEditCity;
import com.example.nardigabriele.mymeta.dialog.DialogGeneral;


public class MainActivity extends AppCompatActivity implements  DialogGeneral.IDialogGeneralListener ,
                                                                DialogDelete.IDialogDeleteListener,
                                                                DialogEditCity.IDialogEditListener,
                                                                DialogChoose.IDialogChooseListener, LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAGDIALOG = "LOLOLOLOLOLOLOL";
    private static final String TAGDIALOGCHOOSE = "LOLOLOLOLOLOLOL";
    CityCursorAdapter cityAdapter;

    public static final String ITEM_ID="ITEM_ID";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView vList = (ListView) findViewById(R.id.list_view);

        cityAdapter = new CityCursorAdapter(this, null);
        vList.setAdapter(cityAdapter);

        getLoaderManager().initLoader(0, null, this);

        Button vBtn = (Button) findViewById(R.id.button2);
        vBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGeneral dialogGeneral = new DialogGeneral();
                dialogGeneral.show(getSupportFragmentManager(), TAGDIALOG);

            }
        });
        vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vIntent = new Intent(MainActivity.this, CityInfo.class);
                Bundle vBundle = new Bundle();
                vBundle.putLong(ITEM_ID, id);
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
            }
        });


        vList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DialogChoose dialogChoose= DialogChoose.getInstance(id);
                dialogChoose.show(getSupportFragmentManager(),TAGDIALOGCHOOSE);

                return true;
            }

        });


    }

    private void addValue(EditText city, EditText data, EditText temp) {

        ContentValues vValuesCity = new ContentValues();

        vValuesCity.put(cityTable.NAME, city.getText().toString());

        getContentResolver().insert(CityContentProvider.CITY_URI, vValuesCity);
        ContentValues vValuesTemp = new ContentValues();
        vValuesTemp.put(tempTable.NAME, city.getText().toString());
        Log.d("SALVATAGGIO NOME CITTA", "FATTO");
        vValuesTemp.put(tempTable.DATE, data.getText().toString());
        Log.d("SALVATAGGIO DATA ", "FATTO");
        vValuesTemp.put(tempTable.TEMP, temp.getText().toString());
        Log.d("SALVATAGGIO TEMPERATURA", "FATTO");

        getContentResolver().insert(TempContentProvider.TEMP_URI, vValuesTemp);


    }


    @Override
    public void insertINFO(EditText city, EditText data, EditText temp) {
        addValue(city,data,temp);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader =
                new CursorLoader(this, CityContentProvider.CITY_URI, null, null, null, null);
        return cursorLoader;

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cityAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
//function of chooseDialog
    @Override
    public void chooseDelorEdit(Boolean choose, long vId) {
        if(choose){
            DialogEditCity dialogEditCity = DialogEditCity.getInstance(vId);
            dialogEditCity.show(getSupportFragmentManager(),"EDIT_TAG");
        }else{
            DialogDelete dialogDelete= DialogDelete.getInstance(vId);
            dialogDelete.show(getSupportFragmentManager(),"TAG_DELETE");
        }

    }
//function of deletDialog
    @Override
    public void deleteInDb(long aItemid) {

        Uri uriToDelete = Uri.parse(CityContentProvider.CITY_URI + "/" + aItemid);

        getContentResolver().delete(uriToDelete, null, null);
        uriToDelete=Uri.parse(TempContentProvider.TEMP_URI+"/"+aItemid);
        getContentResolver().delete(uriToDelete,null,null);

    }
    //function updating City
   @Override
    public void updateInfoCity(long vID, EditText Value) {

           Uri uriToEdit = Uri.parse(TempContentProvider.TEMP_URI +"/" + vID);
            ContentValues contentValues= new ContentValues();
            contentValues.put(tempTable.NAME, Value.getText().toString());
            getContentResolver().update(uriToEdit,contentValues,null,null);

            Uri uri = Uri.parse(CityContentProvider.CITY_URI+"/"+ vID);
            ContentValues cValues= new ContentValues();
            cValues.put(cityTable.NAME, Value.getText().toString());
            getContentResolver().update(uri,cValues,null,null);
       }


}

