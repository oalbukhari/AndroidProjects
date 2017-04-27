package it.damianogiusti.contentprovider.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import it.damianogiusti.contentprovider.R;

/**
 * Created by fedom on 23/12/2016.
 */

public class ContactCursorAdapter extends CursorAdapter
{
    public ContactCursorAdapter(Context aContext,Cursor aCursor){
        super(aContext,aCursor,false);
    }
    class ViewHolder {
        TextView txt_id,txt_name,txt_surname;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list_item,null);
        ViewHolder vHolder= new ViewHolder();
        vHolder.txt_name=(TextView)view.findViewById(R.id.txtName);
        vHolder.txt_surname=(TextView)view.findViewById(R.id.txtSurname);
        view.setTag(vHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        ViewHolder viewHolder=(ViewHolder) view.getTag();
        viewHolder.txt_name.setText(cursor.getString(cursor.getColumnIndex(ContactHelper.NAME)));
        viewHolder.txt_surname.setText(cursor.getString(cursor.getColumnIndex(ContactHelper.SURNAME)));

    }
}
