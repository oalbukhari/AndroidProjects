package com.omar_al_bukhari.weatherprojectnew.Model;

import android.widget.ListView;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class Temperature extends RealmObject {

    @PrimaryKey
    private String id;

    private double temperatureC;
    private double TemperatureF;
    private String date;
    private String weather;

    public Temperature() {
    }

    public Temperature( double temperatureC, double getTemperatureF, String date, String weather) {

        this.temperatureC = temperatureC;
        this.TemperatureF = TemperatureF;
        this.date = date;
        this.weather = weather;
    }


    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
    }

    public void setTemperatureF(double TemperatureF) {
        this.TemperatureF = TemperatureF;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }


    public double getTemperatureC() {
        return temperatureC;
    }

    public double getTemperatureF() {
        return TemperatureF;
    }

    public String getDate() {
        return date;
    }

    public String getWeather() {
        return weather;
    }
}
