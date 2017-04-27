package com.example.fedom.maybeprojectwork.FragmentActivities;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.ActionBarContainer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.fedom.maybeprojectwork.ContentProvider.AnagrafeContentProvider;
import com.example.fedom.maybeprojectwork.CursorAdapter.DetailCursorAdapter;
import com.example.fedom.maybeprojectwork.Database.AnagrafeTabella;
import com.example.fedom.maybeprojectwork.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailContact extends Fragment implements  android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>{
    private long iDentification;
    private static long iud ;
    private DetailCursorAdapter detailCursorAdapter;
    private ListView detailList;


    public static final String TAG_ID ="TAGID";

    public interface IDetailContact {
        void DetailDelete(long id);
    }

    private IDetailContact listener = new IDetailContact() {
        @Override
        public void DetailDelete( long id) {
        }
    };

    public static DetailContact newInstance( long iDentification){
        DetailContact detailContact = new DetailContact();
        Bundle bundle= new Bundle();
        bundle.putLong(TAG_ID,iDentification);
        iud=bundle.getLong(TAG_ID);
        detailContact.setArguments(bundle);
        return detailContact;
    }


    public DetailContact() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_detail_contact, container, false);
        ActionBarContainer actionBarContainer = (ActionBarContainer)view.findViewById(R.id.edit_contact);
        actionBarContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling dialog to modify
            }
        });
        detailList=(ListView)view.findViewById(R.id.listDetail);
        detailCursorAdapter= new DetailCursorAdapter(getActivity(),null);
        detailList.setAdapter(detailCursorAdapter);
        getLoaderManager().initLoader(1,null,this);
        ActionBarContainer deleteAction =(ActionBarContainer)view.findViewById(R.id.delete);
        deleteAction.setEnabled(true);
        ImageView deleteImage = (ImageView)view.findViewById(R.id.deleteImage);
        deleteImage.setEnabled(true);
        deleteAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            listener.DetailDelete(iud);

            }
        });

        return view;

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(getContext(), AnagrafeContentProvider.ANAGRAFE_URI, null, AnagrafeTabella._ID+" = "+iud, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        detailCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailContact.IDetailContact){
            listener=(IDetailContact)context;
        }
    }



}
