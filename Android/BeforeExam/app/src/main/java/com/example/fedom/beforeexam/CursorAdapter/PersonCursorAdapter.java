package com.example.fedom.beforeexam.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.fedom.beforeexam.Database.PersonTable;
import com.example.fedom.beforeexam.R;

/**
 * Created by fedom on 21/02/2017.
 */

public class PersonCursorAdapter extends CursorAdapter {

    public PersonCursorAdapter (Context context, Cursor cursor){
        super(context,cursor,false);
    }
    class ViewHolder {
        TextView txt_name;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.namelist,null);
        ViewHolder vHolder= new ViewHolder();
        vHolder.txt_name=(TextView)view.findViewById(R.id.txtName);


        view.setTag(vHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder=(ViewHolder) view.getTag();

        viewHolder.txt_name.setText(cursor.getString(cursor.getColumnIndex(PersonTable.NAME))+"");
    }
}
