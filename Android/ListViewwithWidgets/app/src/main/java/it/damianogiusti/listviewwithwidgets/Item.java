package it.damianogiusti.listviewwithwidgets;

import java.util.Random;

/**
 * Created by Damiano Giusti on 11/11/16.
 */
public class Item {

    private String name;
    private int progress;
    private float rating;

    public Item(String name) {
        this.name = name;
        Random random = new Random();
        this.progress = random.nextInt(100);
        this.rating = random.nextInt(4);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
