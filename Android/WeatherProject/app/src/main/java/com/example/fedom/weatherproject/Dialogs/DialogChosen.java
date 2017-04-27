package com.example.fedom.weatherproject.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.fedom.weatherproject.MainActivity;

/**
 * Created by fedom on 24/01/2017.
 */

public class DialogChosen extends DialogFragment {
    public interface IDialogChosenListener {
        void DeleteOrEdit(Boolean choose, long vInfo);
    }

    private IDialogChosenListener listener = new IDialogChosenListener() {

        @Override
        public void DeleteOrEdit(Boolean choose,long vInfo){

        }
    };

    public static DialogChosen getInstance(long aItemID){
        DialogChosen vFrag = new DialogChosen();
        Bundle vBundle = new Bundle();
        vBundle.putLong(MainActivity.ITEM_ID, aItemID);
        vFrag.setArguments(vBundle);
        return  vFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IDialogChosenListener) {
            listener = (IDialogChosenListener)activity;
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final long vId = getArguments().getLong(MainActivity.ITEM_ID);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage("WHAT DO YOU WANT TO DO?")
                .setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.DeleteOrEdit(true,vId);
                    }
                })
                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.DeleteOrEdit(false,vId);
                    }
                });
        return builder.create();
    }
}
