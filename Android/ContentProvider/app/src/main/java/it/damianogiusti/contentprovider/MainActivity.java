package it.damianogiusti.contentprovider;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Random;

import it.damianogiusti.contentprovider.database.ContactCursorAdapter;
import it.damianogiusti.contentprovider.database.ContactHelper;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    ContactCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView vList= (ListView) findViewById(R.id.contactsListView);
        adapter=new ContactCursorAdapter(this,null);
        vList.setAdapter(adapter);
        getLoaderManager().initLoader(0,null,this);
        vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deletItem(id);
            }
        });


        Button vButton= (Button) findViewById(R.id.btnAddContact);
        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addValue();
            }
        });


    }

    private void deletItem(long id){
        Uri uritodelete= Uri.parse(ContactsContentProvider.CONTACTS_URI+"/"+id);
        getContentResolver().delete(uritodelete,null,null);
    }


    private void addValue(){
        Random vRand= new Random();
        ContentValues vValues=new ContentValues();
        int a= vRand.nextInt();
        vValues.put(ContactHelper.NAME,"nome "+a);
        vValues.put(ContactHelper.SURNAME,"nome "+a);
        getContentResolver().insert(ContactsContentProvider.CONTACTS_URI,vValues);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader= new CursorLo  dialog.show(getSupportFragmentManager(),TAGDIALOG);
        ader(this,ContactsContentProvider.CONTACTS_URI,null,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


    }
}
