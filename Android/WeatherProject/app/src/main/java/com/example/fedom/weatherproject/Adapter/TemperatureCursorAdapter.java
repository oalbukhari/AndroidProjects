package com.example.fedom.weatherproject.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.fedom.weatherproject.DataBase.TemperatureTable;
import com.example.fedom.weatherproject.R;

import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Created by fedom on 24/01/2017.
 */

public class TemperatureCursorAdapter extends CursorAdapter {

    public TemperatureCursorAdapter(Context context, Cursor cursor){
        super(context,cursor,false);
    }
    class ViewHolder {
        TextView txt_data, txt_temp;

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_temperature_data,null);
        ViewHolder vHolder= new ViewHolder();
        vHolder.txt_data=(TextView)view.findViewById(R.id.txtData);
        vHolder.txt_temp=(TextView)view.findViewById(R.id.txtTemp);

        view.setTag(vHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder=(ViewHolder) view.getTag();

        viewHolder.txt_temp.setText(cursor.getFloat(cursor.getColumnIndex(TemperatureTable.TEMPERATURE))+"");
        viewHolder.txt_data.setText(cursor.getString(cursor.getColumnIndex(TemperatureTable.DATE)));


    }
}
