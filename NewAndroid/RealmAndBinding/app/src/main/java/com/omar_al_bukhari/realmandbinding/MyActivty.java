package com.omar_al_bukhari.realmandbinding;

import android.app.Application;

import io.realm.Realm;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class MyActivty extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
