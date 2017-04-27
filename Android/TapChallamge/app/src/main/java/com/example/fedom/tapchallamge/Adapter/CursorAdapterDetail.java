package com.example.fedom.tapchallamge.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.fedom.tapchallamge.Database.ScoreInfoTabel;
import com.example.fedom.tapchallamge.R;

/**
 * Created by fedom on 23/02/2017.
 */

public class CursorAdapterDetail extends CursorAdapter {
    public CursorAdapterDetail  (Context context, Cursor cursor){
    super(context,cursor,false);
}
    class ViewHolder{
        TextView txt_name,txt_score;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutinsertplayer,null);
        ViewHolder vHolder= new ViewHolder();
        vHolder.txt_name=(TextView)view.findViewById(R.id.namePlayer);
        vHolder.txt_score=(TextView)view.findViewById(R.id.scorePlayer);
        view.setTag(vHolder);

        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder=(ViewHolder) view.getTag();
        viewHolder.txt_name.setText(cursor.getString(cursor.getColumnIndex(ScoreInfoTabel.NAME)));
        viewHolder.txt_score.setText(cursor.getString(cursor.getColumnIndex(ScoreInfoTabel.SCORE)));
    }
}
