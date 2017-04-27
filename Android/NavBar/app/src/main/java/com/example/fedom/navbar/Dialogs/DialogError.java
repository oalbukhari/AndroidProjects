package com.example.fedom.navbar.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.DialogFragment;

import com.example.fedom.navbar.R;

/**
 * Created by fedom on 30/12/2016.
 */

public class DialogError extends DialogFragment {


    public interface IDialogErrorListener  {
        void errore();
    }

    IDialogErrorListener listener = new IDialogErrorListener() {
        @Override
        public void errore() {

        }
    };


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof  IDialogErrorListener){
            listener = (IDialogErrorListener)activity;
        }

    }

    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.activity_login,null));
        builder.setPositiveButton("SIGNIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();



    }
}
