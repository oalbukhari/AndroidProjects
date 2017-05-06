package com.omar_al_bukhari.weatherprojectnew.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar_al_bukhari.weatherprojectnew.Model.City;
import com.omar_al_bukhari.weatherprojectnew.Model.Temperature;
import com.omar_al_bukhari.weatherprojectnew.R;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class GeneralHomeAdapter extends RecyclerView.Adapter<ViewHolderGenericHome> {
    private RealmResults<City>city ;


    public GeneralHomeAdapter(RealmResults<City> city) {
        this.city = city;

    }


    @Override
    public ViewHolderGenericHome onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.visualizing_general,parent,false);
        return new ViewHolderGenericHome(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderGenericHome holder, int position) {
        final City vCity= city.get(position);
        Temperature vTemp= city.get(position).getTemperature().last();
        holder.cityText.setText(vCity.getNameCity().toString());
        holder.dateText.setText(vTemp.getDate());
        holder.textTemperature.setText(vTemp.getTemperatureC()+"°C");
    }

    @Override
    public int getItemCount() {
        return city.size();
    }
}
