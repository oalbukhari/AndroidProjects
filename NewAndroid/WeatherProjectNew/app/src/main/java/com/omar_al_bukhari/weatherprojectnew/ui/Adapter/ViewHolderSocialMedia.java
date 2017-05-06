package com.omar_al_bukhari.weatherprojectnew.ui.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.omar_al_bukhari.weatherprojectnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 05/05/2017
 * Any copy of this code is forbidden.
 */

public class ViewHolderSocialMedia extends RecyclerView.ViewHolder {
    @BindView(R.id.imageViewSocial)ImageView socialMediaView;

    public ViewHolderSocialMedia(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }



}
