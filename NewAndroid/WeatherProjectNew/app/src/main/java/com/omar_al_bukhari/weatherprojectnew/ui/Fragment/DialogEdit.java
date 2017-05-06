package com.omar_al_bukhari.weatherprojectnew.ui.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.omar_al_bukhari.weatherprojectnew.Model.City;
import com.omar_al_bukhari.weatherprojectnew.Model.JsonReader;
import com.omar_al_bukhari.weatherprojectnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class DialogEdit extends DialogFragment {
 //   @BindView(R.id.insertButton)Button insertButton;
    @BindView(R.id.editCity)EditText editCityDialog;
    Button insertButton;


    public interface IDialogGeneralListener{
        void insertCity(String name);
    }
    IDialogGeneralListener listener= new IDialogGeneralListener(){
        @Override
        public void insertCity(String city) {

        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof IDialogGeneralListener){
            listener = (IDialogGeneralListener) activity;
        }
    }

    JsonReader jsonReader= new JsonReader();

    public Dialog onCreateDialog (Bundle savedinstace){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_add_temperature, null);
        insertButton=ButterKnife.findById(view,R.id.insertButton);
        ButterKnife.bind(this,view);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editCityDialog.getText().toString().matches("")) {
                    Toast.makeText(view.getContext(),"Devi inserire una città",Toast.LENGTH_LONG).show();
                }else {
                    Log.d("TAG", "test");
                    listener.insertCity(editCityDialog.getText().toString());
                    Toast.makeText(view.getContext(),"La città è stata inserita",Toast.LENGTH_SHORT).show();
                    dismiss();

                }
            }
        });
        builder.setView(view);
        return builder.create();
    }

}
