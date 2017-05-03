package com.omar_al_bukhari.realmandbinding;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class ListItem extends RealmObject {

    private String name;
    private String age;

    public ListItem() {
    }

    public ListItem(String name, String age) {
        this.name = name;
        this.age = age;

    }



    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
