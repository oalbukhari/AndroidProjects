package com.example.fedom.maybeprojectwork.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.fedom.maybeprojectwork.FragmentActivities.DetailContact;

/**
 * Created by fedom on 14/02/2017.
 */

public class DialogElimina extends DialogFragment {


    private static final String TAG="DELETE_DIALOG";
    private static long iDentification;
    private static long iud;



    public interface IDialogEliminaListener {
        void deleteInDb(boolean value,long id);
    }

    private IDialogEliminaListener listener = new IDialogEliminaListener() {
        @Override
        public void deleteInDb(boolean value,long id) {

        }
    };


    public static DialogElimina getInstance(long aItemID){
        DialogElimina vFrag = new DialogElimina();
        Bundle vBundle = new Bundle();
        vBundle.putLong(TAG,iDentification);
        iud=aItemID;
        vFrag.setArguments(vBundle);
        return  vFrag;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final long vId = getArguments().getLong(DetailContact.TAG_ID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage("VUOI ELIMINARE?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.deleteInDb(true,iud);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DialogElimina.IDialogEliminaListener){
            listener=(IDialogEliminaListener)context;
        }
    }

}
