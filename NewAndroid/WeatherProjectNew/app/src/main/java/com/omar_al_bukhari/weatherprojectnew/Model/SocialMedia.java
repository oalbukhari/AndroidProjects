package com.omar_al_bukhari.weatherprojectnew.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class SocialMedia extends RealmObject {

    @PrimaryKey
    private String id;

    private String Name;

    public SocialMedia() {
    }


    public SocialMedia(String id, String name) {
        this.id = id;
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }
}
