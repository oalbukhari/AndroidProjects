package com.example.fedom.weatherproject.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.example.fedom.weatherproject.MainActivity;

/**
 * Created by fedom on 26/01/2017.
 */

public class DialogDelete extends DialogFragment {


    private static final String TAG="DELETE_DIALOG";

    public interface IDialogDeleteListener {
        void deleteInDb(long aItemid);
    }

    private DialogDelete.IDialogDeleteListener listener = new DialogDelete.IDialogDeleteListener() {
        @Override
        public void deleteInDb(long aItemid) {
            // dummmy init
            Log.d(TAG, "must Delete");
        }
    };


    public static DialogDelete getInstance(long aItemID){
        DialogDelete vFrag = new DialogDelete();
        Bundle vBundle = new Bundle();
        vBundle.putLong(MainActivity.ITEM_ID, aItemID);
        vFrag.setArguments(vBundle);
        return  vFrag;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DialogChosen.IDialogChosenListener) {
            listener = (DialogDelete.IDialogDeleteListener)activity;
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final long vId = getArguments().getLong(MainActivity.ITEM_ID);
        builder
                .setMessage("ARE YOU SURE?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.deleteInDb(vId);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

}
