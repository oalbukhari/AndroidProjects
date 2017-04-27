package com.example.fedom.weatherproject.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.fedom.weatherproject.DataBase.CityTable;
import com.example.fedom.weatherproject.R;

/**
 * Created by fedom on 24/01/2017.
 */

public class CityCursorAdapter extends CursorAdapter {

    public CityCursorAdapter (Context context, Cursor cursor){
        super(context,cursor,false);
    }
    class ViewHolder{
        TextView txt_city,txt_type;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.city_list,null);
            ViewHolder vHolder= new ViewHolder();

            vHolder.txt_city=(TextView)view.findViewById(R.id.txtCity);
            vHolder.txt_type=(TextView)view.findViewById(R.id.textImpl);
            view.setTag(vHolder);

            return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder=(ViewHolder) view.getTag();
        viewHolder.txt_city.setText(cursor.getString(cursor.getColumnIndex(CityTable.NAME)));
        viewHolder.txt_type.setText("CITY NAME:");
    }
}
