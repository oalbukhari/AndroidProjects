package com.example.nardigabriele.mymeta.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.nardigabriele.mymeta.MainActivity;
import com.example.nardigabriele.mymeta.R;
import com.example.nardigabriele.mymeta.data.CityContentProvider;
import com.example.nardigabriele.mymeta.data.cityTable;

/**
 * Created by fedom on 18/01/2017.
 */

public class DialogEditCity extends DialogFragment   {

    public EditText txtAnyEdit;

    private static final String TAG="EDIT_DIALOG";



    public interface IDialogEditListener {
        void updateInfoCity(long idPass,EditText Value);
    }

    private DialogEditCity.IDialogEditListener listener = new DialogEditCity.IDialogEditListener() {
        @Override
        public void updateInfoCity(long id, EditText Value) {
            // dummmy init
            Log.d(TAG, "edit_the_dialog");
        }
    };

    public static DialogEditCity getInstance(long aItemID){
        DialogEditCity vFrag = new DialogEditCity();
        Bundle vBundle = new Bundle();
        vBundle.putLong(MainActivity.ITEM_ID, aItemID);
        vFrag.setArguments(vBundle);
        return  vFrag;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DialogEditCity.IDialogEditListener) {
            listener = (DialogEditCity.IDialogEditListener)activity;
        }
    }


    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final long vId = getArguments().getLong(MainActivity.ITEM_ID);

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_activity_city, null);
        builder.setView(view);

        txtAnyEdit = (EditText) view.findViewById(R.id.editText);


        if(savedInstance==null){
            Uri uriMade= Uri.parse(CityContentProvider.CITY_URI+"/"+vId);
            Cursor vCursor = getContext().getContentResolver().query(uriMade,null,null,null,null);
            while (vCursor.moveToNext()){
                txtAnyEdit.setText("" + vCursor.getString(vCursor.getColumnIndex(cityTable.NAME)));
            }
            vCursor.close();
        }



        builder.setMessage("Modifica il nome città")
                .setPositiveButton("Modifica", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.updateInfoCity(vId,txtAnyEdit);

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
