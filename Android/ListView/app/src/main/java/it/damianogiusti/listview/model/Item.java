package it.damianogiusti.listview.model;

import java.util.Random;

/**
 * Created by Damiano Giusti on 11/11/16.
 */
public class Item {
    private int id;
    private String name;
    private int liters;

    public Item(int id) {
        this.id = id;
        this.name = "Beer #" + id;
        Random random = new Random();
        this.liters = random.nextInt(10);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLiters() {
        return liters;
    }

    public void setLiters(int liters) {
        this.liters = liters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
