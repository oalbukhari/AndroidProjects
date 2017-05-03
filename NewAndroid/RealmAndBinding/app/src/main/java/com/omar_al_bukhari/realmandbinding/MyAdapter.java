package com.omar_al_bukhari.realmandbinding;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import io.realm.RealmResults;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private RealmResults<ListItem> list;
    private Context context;
    private ListItem listItem;

    public MyAdapter(RealmResults<ListItem> list, Context context) {
        this.list = list;
        this.context = context;
        setHasStableIds(true);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ListItem listItem= list.get(position);
        holder.txtAge.setText(listItem.getAge());
        holder.txtName.setText(listItem.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
