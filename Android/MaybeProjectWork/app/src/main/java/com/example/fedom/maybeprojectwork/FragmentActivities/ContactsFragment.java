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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.fedom.maybeprojectwork.ContentProvider.AnagrafeContentProvider;
import com.example.fedom.maybeprojectwork.CursorAdapter.AnagrafeCursorAdapter;
import com.example.fedom.maybeprojectwork.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ContactsFragment extends Fragment implements  android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {
    AnagrafeCursorAdapter anagrafeCursorAdapter;

    public interface IContactsFragment {
        void getInfoContact(long iD);
        void addNewContact();
    }

    IContactsFragment iContactsFragment= new IContactsFragment() {
        @Override
        public void getInfoContact(long iD) {
        }
        @Override
        public void addNewContact(){
        }
    };

    public ContactsFragment() {
        // Required empty public constructor
    }

    public static ContactsFragment newIstance(){
        ContactsFragment contactsFragment = new ContactsFragment();
        Bundle bundle= new Bundle();
        contactsFragment.setArguments(bundle);
        return contactsFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contacts, container, false);
        ListView vList = (ListView)view.findViewById(R.id.list_contacts);

        anagrafeCursorAdapter=new AnagrafeCursorAdapter(getActivity(),null);
        vList.setAdapter(anagrafeCursorAdapter);
        getLoaderManager().initLoader(0,null, this);

        vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iContactsFragment.getInfoContact(id);
            }
        });

        ActionBarContainer editAction = (ActionBarContainer) view.findViewById(R.id.edit_contact);
        editAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iContactsFragment.addNewContact();
            }
        });
        ActionBarContainer deleteAction =(ActionBarContainer)view.findViewById(R.id.delete);
        deleteAction.setEnabled(false);
        deleteAction.setVisibility(View.GONE);
        ImageView deleteImage = (ImageView)view.findViewById(R.id.deleteImage);
        deleteImage.setEnabled(false);
        deleteImage.setVisibility(View.GONE);


        return view;
    }

//METHODS CURSOR
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(getContext(), AnagrafeContentProvider.ANAGRAFE_URI, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        anagrafeCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }



    @Override
    public void onDetach() {
        super.onDetach();
        iContactsFragment=null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IContactsFragment){
            iContactsFragment=(IContactsFragment)context;
        }
    }
}
