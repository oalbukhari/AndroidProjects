package com.example.nardigabriele.mymeta.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.nardigabriele.mymeta.MainActivity;

/**
 * Created by fedom on 18/01/2017.
 */

public class DialogChoose extends DialogFragment {


    private static final String TAG_DELETE="CHOOSE_DIALOG_DELETE";
    private static final String TAG_EDIT="CHOOSE_DIALOG_EDIT";

    public interface IDialogChooseListener {
        void chooseDelorEdit(Boolean choose, long vInfo);
    }

    private IDialogChooseListener listener = new IDialogChooseListener() {

        @Override
        public void chooseDelorEdit(Boolean choose,long vInfo){

        }
    };

    public static DialogChoose getInstance(long aItemID){
        DialogChoose vFrag = new DialogChoose();
        Bundle vBundle = new Bundle();
        vBundle.putLong(MainActivity.ITEM_ID, aItemID);
        vFrag.setArguments(vBundle);
        return  vFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IDialogChooseListener) {
            listener = (IDialogChooseListener)activity;
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final long vId = getArguments().getLong(MainActivity.ITEM_ID);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage("COSA SCEGLI DI FARE?")
                .setPositiveButton("MODIFICA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.chooseDelorEdit(true,vId);
                    }
                })
                .setNegativeButton("CANCELLA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.chooseDelorEdit(false,vId);
                    }
                });
        return builder.create();
    }

}
