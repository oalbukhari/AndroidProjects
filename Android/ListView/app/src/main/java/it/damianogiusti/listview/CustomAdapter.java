package it.damianogiusti.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import it.damianogiusti.listview.model.Item;

/**
 * Created by Damiano Giusti on 11/11/16.
 */
public class CustomAdapter extends BaseAdapter {

    private static final String TAG = "CustomAdapter";

    private Context context;
    private ArrayList<Item> database;

    public CustomAdapter(Context context, ArrayList<Item> database) {
        this.context = context;
        this.database = database;
    }

    @Override
    public int getCount() {
        return database.size();
    }

    @Override
    public Item getItem(int i) {
        return database.get(i);
    }

    @Override
    public long getItemId(int i) {
        return database.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        ItemViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.item_row_layout, null);
            viewHolder = new ItemViewHolder(rowView);
            rowView.setTag(viewHolder);
        } else {
            rowView = view;
            viewHolder = (ItemViewHolder) view.getTag();
        }

        Item item = getItem(i);
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtValue.setText(String.valueOf(item.getLiters()));

        return rowView;
    }

    private static class ItemViewHolder {
        private TextView txtName;
        private TextView txtValue;

        ItemViewHolder(View itemView) {
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtValue = (TextView) itemView.findViewById(R.id.txtValue);
        }
    }
}
