package com.omar_al_bukhari.realmandbinding;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<ListItem> list;
    private Context context;

    public MyAdapter(List<ListItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem listItem= list.get(position);
        holder.txtAge.setText(listItem.getAge());
        holder.txtName.setText(listItem.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
