package com.example.nardigabriele.mymeta.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.icu.text.SimpleDateFormat;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;


import com.example.nardigabriele.mymeta.MainActivity;
import com.example.nardigabriele.mymeta.R;

import java.text.Format;
import java.util.Date;


/**
 * Created by fedom on 18/01/2017.
 */

public class DialogGeneral extends DialogFragment {


    private  EditText txtCityEdit;
    private EditText txtDataEdit;
    private EditText txtTempEdit;
    private Switch switchDateAuto;
    private LinearLayout layoutData;
    private long mDate;




    public interface IDialogGeneralListener {
        void insertINFO(EditText city, EditText data, EditText temp);
    }

    IDialogGeneralListener listener = new IDialogGeneralListener() {
        @Override
        public void insertINFO(EditText city, EditText data, EditText temp) {

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.insert_dialog, null);
        builder.setView(view);

        txtCityEdit = (EditText) view.findViewById(R.id.edit_city);
        txtDataEdit = (EditText) view.findViewById(R.id.edit_data);
        txtTempEdit = (EditText) view.findViewById(R.id.edit_Temperature);
        layoutData = (LinearLayout) view.findViewById(R.id.layout_data_manuale);

        switchDateAuto = (Switch) view.findViewById(R.id.switch_date);
        switchDateAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchDateAuto.isChecked()){
                    mDate = new Date().getTime();
                   // layoutData.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,0f));
                    Format vFormatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String vDate = vFormatter.format(mDate);
                    txtDataEdit.setText(vDate);

                }else{
                  //  layoutData.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1f));
                    txtDataEdit.setText("");


                }
            }
        });


        builder.setPositiveButton("INSERISCI DATI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                listener.insertINFO(txtCityEdit,txtDataEdit,txtTempEdit);


            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();


    }
}





