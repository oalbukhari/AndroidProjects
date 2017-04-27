package com.example.fedom.weatherproject.Fragments_NOT_FINISHED;


import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fedom.weatherproject.ContentProvider.FullContentProvider;
import com.example.fedom.weatherproject.DataBase.TemperatureTable;
import com.example.fedom.weatherproject.Dialogs.DialogUpdateTemperature;
import com.example.fedom.weatherproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoTemperature extends Fragment implements DialogUpdateTemperature.IDialogEditListener, LoaderManager.LoaderCallbacks<Cursor> {
    public static final String TAG="TAG_ID";
    private ListView listTemperature;

    private long iDentification;

  /* public interface IInfoTemperature{

      void  insert();


    }
    private IInfoTemperature listener= new IInfoTemperature() {
        @Override
        public void insert(){

        }
    };*/

    public FragmentInfoTemperature() {
        // Required empty public constructor
    }
    public static FragmentInfoTemperature newInstance(long id) {
        FragmentInfoTemperature fragmentInfoTemperature= new FragmentInfoTemperature();
        Bundle args = new Bundle();
        args.putLong(TAG,id);
        fragmentInfoTemperature.setArguments(args);
        return fragmentInfoTemperature;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_info_temperature, container, false);
        listTemperature= (ListView)view.findViewById(R.id.list_info);

        listTemperature.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUpdateTemperature updateTemperature = DialogUpdateTemperature.getInstance(id);
                updateTemperature.show(getFragmentManager(),"EDIT_TAG");
                return true;
            }
        });
        return view;

    }

    @Override
    public void updateInfoTemperature(long idPass, EditText Value) {
        Uri uri = Uri.parse(FullContentProvider.URI_TEMPERATURE+"/"+ idPass);
        ContentValues cValues= new ContentValues();
        cValues.put(TemperatureTable.TEMPERATURE, Value.getText().toString());
      //  getContentResolver().update(uri,cValues,null,null);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
