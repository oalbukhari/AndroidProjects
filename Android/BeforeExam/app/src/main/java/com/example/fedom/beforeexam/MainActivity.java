package com.example.fedom.beforeexam;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.fedom.beforeexam.ContentProvider.ContentProviderPerson;
import com.example.fedom.beforeexam.CursorAdapter.PersonCursorAdapter;
import com.example.fedom.beforeexam.Database.PersonTable;
import com.example.fedom.beforeexam.Fragments.DialogInsert;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,DialogInsert.IDialogGeneralListener{
    public static final String TAG_DIALOG_INSERT="tagdialoginsert";
    ListView listname;
    Button addPerson;
    PersonCursorAdapter personCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listname=(ListView)findViewById(R.id.listName);
        addPerson=(Button)findViewById(R.id.button);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInsert dialogInsert = new DialogInsert();
                dialogInsert.show(getSupportFragmentManager(),TAG_DIALOG_INSERT);

            }
        });
        personCursorAdapter = new PersonCursorAdapter(this,null);
        listname.setAdapter(personCursorAdapter);
        getLoaderManager().initLoader(0,null,this);

        listname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader =
                new CursorLoader(this, ContentProviderPerson.URI_PERSON, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        personCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void insertINFO(Person person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersonTable.NAME,person.getNome());
        contentValues.put(PersonTable.SURNAME,person.getCognome());
        contentValues.put(PersonTable.AGE,person.getEta());
        getContentResolver().insert(ContentProviderPerson.URI_PERSON,contentValues);
    }

}
