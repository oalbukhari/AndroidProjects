package com.example.fedom.weatherproject.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.fedom.weatherproject.ContentProvider.FullContentProvider;
import com.example.fedom.weatherproject.DataBase.CityTable;
import com.example.fedom.weatherproject.MainActivity;
import com.example.fedom.weatherproject.R;


/**
 * Created by fedom on 24/01/2017.
 */

public class DialogUpdateCity extends DialogFragment {

    public EditText txtAnyEdit;

    private static final String TAG="EDIT_DIALOG";



    public interface IDialogEditListener {
        void updateInfoCity(long idPass,EditText Value);
    }

    private DialogUpdateCity.IDialogEditListener listener = new DialogUpdateCity.IDialogEditListener() {
        @Override
        public void updateInfoCity(long id, EditText Value) {
            // dummmy init
            Log.d(TAG, "edit_the_dialog");
        }
    };

    public static DialogUpdateCity getInstance(long aItemID){
        DialogUpdateCity vFrag = new DialogUpdateCity();
        Bundle vBundle = new Bundle();
        vBundle.putLong(MainActivity.ITEM_ID, aItemID);
        vFrag.setArguments(vBundle);
        return  vFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DialogUpdateCity.IDialogEditListener) {
            listener = (DialogUpdateCity.IDialogEditListener)activity;
        }
    }


    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final long vId = getArguments().getLong(MainActivity.ITEM_ID);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_activity_city_and_temperature, null);
        builder.setView(view);
        txtAnyEdit = (EditText) view.findViewById(R.id.editText);
        if(savedInstance==null){
            Uri uriMade= Uri.parse(FullContentProvider.URI_CITY+"/"+vId);
            Cursor vCursor = getContext().getContentResolver().query(uriMade,null,null,null,null);
            while (vCursor.moveToNext()){
                txtAnyEdit.setText("" + vCursor.getString(vCursor.getColumnIndex(CityTable.NAME)));
            }
            vCursor.close();
        }
        builder.setMessage("MODIFY INFO")
                .setPositiveButton("DONE ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.updateInfoCity(vId,txtAnyEdit);

                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

}
