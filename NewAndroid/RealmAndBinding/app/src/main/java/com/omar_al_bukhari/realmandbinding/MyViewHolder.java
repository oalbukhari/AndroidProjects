package com.omar_al_bukhari.realmandbinding;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.age)
    TextView txtAge;
    @BindView(R.id.name)
    TextView txtName;
    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
