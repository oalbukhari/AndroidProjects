package com.example.fedom.maybeprojectwork.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fedom.maybeprojectwork.Database.AnagrafeTabella;
import com.example.fedom.maybeprojectwork.R;

/**
 * Created by fedom on 19/01/2017.
 */

public class AnagrafeCursorAdapter extends CursorAdapter {

    public AnagrafeCursorAdapter(Context context, Cursor cursor){
        super(context,cursor,false);
    }

    class ViewHolder {
        TextView txt_nameSurname;

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_anagrafe,null);
        ViewHolder vHolder= new ViewHolder();
        vHolder.txt_nameSurname=(TextView) view.findViewById(R.id.txtNameSurname);
        view.setTag(vHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder=(ViewHolder) view.getTag();
        String name= cursor.getString(cursor.getColumnIndex(AnagrafeTabella.NAME));
        String surname= cursor.getString(cursor.getColumnIndex(AnagrafeTabella.SURNAME));

        viewHolder.txt_nameSurname.setText((name+" "+surname));
    }
}
