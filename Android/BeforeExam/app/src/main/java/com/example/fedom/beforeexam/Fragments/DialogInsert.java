package com.example.fedom.beforeexam.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.fedom.beforeexam.Person;
import com.example.fedom.beforeexam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogInsert extends DialogFragment {
    EditText NAME;
    EditText SURNAME;
    EditText ETA;

    public interface IDialogGeneralListener {
        void insertINFO(Person person);

    }

    IDialogGeneralListener listener = new IDialogGeneralListener() {
        @Override
        public void insertINFO(Person person) {
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IDialogGeneralListener) {
            listener = (IDialogGeneralListener) activity;
        }

    }




    public Dialog onCreateDialog(Bundle savedInstance) {
        // Inflate the layout for this fragment
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_insert, null);
        builder.setView(view);

        NAME=(EditText)view.findViewById(R.id.edit_name);
        SURNAME=(EditText)view.findViewById(R.id.edit_surname);
        ETA=(EditText)view.findViewById(R.id.edit_eta);

        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Person person= new Person(NAME.getText().toString(),SURNAME.getText().toString(),ETA.getText().toString());
                listener.insertINFO(person);

            }
        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        return builder.create();
    }

}
