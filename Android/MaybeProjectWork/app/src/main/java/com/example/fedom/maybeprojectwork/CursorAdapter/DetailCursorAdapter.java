package com.example.fedom.maybeprojectwork.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.fedom.maybeprojectwork.Database.AnagrafeTabella;
import com.example.fedom.maybeprojectwork.R;

/**
 * Created by fedom on 15/02/2017.
 */

public class DetailCursorAdapter extends CursorAdapter {

    public DetailCursorAdapter(Context context, Cursor cursor){
        super(context,cursor,false);
    }

    class ViewHolder {
        TextView tName,tSurname,tAge,tAddress,tCity,tCAP,tProvince,tTelephone,tEmail;

    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.detailview,null);
        DetailCursorAdapter.ViewHolder vHolder= new DetailCursorAdapter.ViewHolder();
        vHolder.tName=(TextView)view.findViewById(R.id.detailName);
        vHolder.tSurname=(TextView)view.findViewById(R.id.detailSurname);
        vHolder.tAge=(TextView)view.findViewById(R.id.detailAge);
        vHolder.tAddress=(TextView)view.findViewById(R.id.detailAddres);
        vHolder.tCity=(TextView)view.findViewById(R.id.detailCity);
        vHolder.tProvince=(TextView)view.findViewById(R.id.detailProvince);
        vHolder.tCAP=(TextView)view.findViewById(R.id.detailCAP);
        vHolder.tEmail=(TextView)view.findViewById(R.id.detailEmail);
        vHolder.tTelephone=(TextView)view.findViewById(R.id.detailPhone);
        view.setTag(vHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder=(ViewHolder) view.getTag();

        viewHolder.tName.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.NAME)));
        viewHolder.tSurname.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.SURNAME)));
        viewHolder.tAge.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.BORN_DATE)));
        viewHolder.tAddress.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.STREET)));
        viewHolder.tCity.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.CITY)));
        viewHolder.tProvince.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.PROVINCE)));
        viewHolder.tCAP.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.CAP)));
        viewHolder.tEmail.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.EMAIL)));
        viewHolder.tTelephone.setText(cursor.getString(cursor.getColumnIndex(AnagrafeTabella.TELEPHONE)));
    }
}
