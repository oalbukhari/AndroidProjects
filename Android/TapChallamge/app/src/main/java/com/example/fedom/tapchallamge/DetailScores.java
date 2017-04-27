package com.example.fedom.tapchallamge;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fedom.tapchallamge.Adapter.CursorAdapterDetail;
import com.example.fedom.tapchallamge.ContentProvider.ScoreContentProvider;

public class DetailScores extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    CursorAdapterDetail cursorAdapterDetail;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scores);

        listView=(ListView)findViewById(R.id.listDetail);
        cursorAdapterDetail = new CursorAdapterDetail(this,null);
        listView.setAdapter(cursorAdapterDetail);

        getLoaderManager().initLoader(1,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader;
        //  Uri vTemp = Uri.parse(TempContentProvider.TEMP_URI + "/" + getTitle());
        cursorLoader = new CursorLoader(this, ScoreContentProvider.URI_SCORE,null,null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapterDetail.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
