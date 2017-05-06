package com.omar_al_bukhari.weatherprojectnew.ui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.omar_al_bukhari.weatherprojectnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }
}
