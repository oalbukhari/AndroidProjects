package it.damianogiusti.listviewwithwidgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Damiano Giusti on 11/11/16.
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> dataset;

    public ItemAdapter(Context context, ArrayList<Item> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Item getItem(int i) {
        return dataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dataset.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        ItemViewHolder viewHolder;

        if (view == null) {
            rowView = LayoutInflater.from(context).inflate(R.layout.item_row_view, null);
            viewHolder = new ItemViewHolder();
            viewHolder.txtName = (TextView) rowView.findViewById(R.id.txtName);
            viewHolder.progressBar = (ProgressBar) rowView.findViewById(R.id.progressBar);
            viewHolder.ratingBar = (RatingBar) rowView.findViewById(R.id.ratingBar);
            rowView.setTag(viewHolder);
        } else {
            rowView = view;
            viewHolder = (ItemViewHolder) view.getTag();
        }

        Item item = getItem(i);
        viewHolder.txtName.setText(item.getName());
        viewHolder.progressBar.setProgress(item.getProgress());
        viewHolder.ratingBar.setRating(item.getRating());

        return rowView;
    }

    private static class ItemViewHolder {
        private TextView txtName;
        private ProgressBar progressBar;
        private RatingBar ratingBar;
    }
}
