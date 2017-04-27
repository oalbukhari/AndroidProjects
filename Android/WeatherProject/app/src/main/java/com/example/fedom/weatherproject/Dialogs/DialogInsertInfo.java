package com.example.fedom.weatherproject.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fedom.weatherproject.CityAndTemp;
import com.example.fedom.weatherproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.util.Date;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

/**
 * Created by fedom on 24/01/2017.
 */

public class DialogInsertInfo extends DialogFragment {

    private  EditText txtCityEdit;
    private EditText txtDataEdit;
    private EditText txtTempEdit;
    private Switch switchDateAuto;
    private Switch switchTemperatureAuto;
    private LinearLayout layoutData;
    private LinearLayout layoutTemperature;

    private long mDate;
    private float mTemperature;
    private CheckBox checkTempAuto;


    public interface IDialogGeneralListener {
        void insertINFO(CityAndTemp info);
        void searchCity(String insert);
    }

    IDialogGeneralListener listener = new IDialogGeneralListener() {
        @Override
        public void insertINFO(CityAndTemp info) {

        }

        @Override
        public void searchCity(String insert) {

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
        layoutTemperature= (LinearLayout) view.findViewById(R.id.layout_temperature_manuale);
        /////
        txtCityEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("WHATCHER:", "BeforeTextChanged"+s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               listener.searchCity(s.toString());
                Log.d("WHATCHER:", "OnTextChanged"+s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
//s               listener.searchCity(s.toString());
                Log.d("WHATCHER:", "AfterTextChanged"+s.toString());
            }
        });

        switchTemperatureAuto = (Switch) view.findViewById(R.id.switch_temperature);
        switchTemperatureAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchTemperatureAuto.isChecked()){
                    layoutTemperature.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,0f));
                    String city = txtCityEdit.getText().toString();
                    if (txtCityEdit != null) {

                        if (switchTemperatureAuto.isChecked()) {
                            // Instantiate the RequestQueue.
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            String url = "http://api.wunderground.com/api/ecad2a61d663ed54/conditions/q/IT/" + city.replace(" ", "_") + ".json";

                            // Request a string response from the provided URL.
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonobject = new JSONObject(response);
                                                JSONObject jsonObject2 = jsonobject.getJSONObject("current_observation");
                                                txtTempEdit.setText(jsonObject2.getString("temp_c"));
                                                txtTempEdit.setTextColor(BLACK);
                                            } catch (JSONException e) {
                                                Log.d("JSON", "NOPE!" + e);
                                                txtTempEdit.setText("Città errata!");
                                                txtTempEdit.setTextColor(RED);
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("JSON", "That didn't work!");
                                }
                            });
                            // Add the request to the RequestQueue.
                            queue.add(stringRequest);
                        }


                    }

                }else{
                    layoutTemperature.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1f));
                    txtTempEdit.setText("");


                }
            }
        });

        switchDateAuto = (Switch) view.findViewById(R.id.switch_date);
        switchDateAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchDateAuto.isChecked()){
                    mDate = new Date().getTime();
                    layoutData.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,0f));
                    Format vFormatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String vDate = vFormatter.format(mDate);
                    txtDataEdit.setText(vDate);
                    Log.d("LA DATA ",vDate);

                }else{
                   layoutData.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1f));
                    txtDataEdit.setText("");


                }
            }
        });


        builder.setPositiveButton("ADD NEW INFO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CityAndTemp cityAndTemp = new CityAndTemp(txtCityEdit.getText().toString(),txtTempEdit.getText().toString(),txtDataEdit.getText().toString());
                listener.insertINFO(cityAndTemp);


            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();


    }

}
