package com.omar_al_bukhari.weatherprojectnew.ui.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.omar_al_bukhari.weatherprojectnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class ViewHolderGenericHome extends RecyclerView.ViewHolder {
    @BindView(R.id.imageWeather)ImageView imageView;
    @BindView(R.id.textCity)TextView cityText;
    @BindView(R.id.textDate)TextView dateText;
    @BindView(R.id.textTemperature)TextView textTemperature;
    public ViewHolderGenericHome(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
