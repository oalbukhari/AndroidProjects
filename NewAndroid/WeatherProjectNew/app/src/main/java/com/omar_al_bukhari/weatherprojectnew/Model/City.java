package com.omar_al_bukhari.weatherprojectnew.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 03/05/2017
 * Any copy of this code is forbidden.
 */

public class City extends RealmObject {
    @PrimaryKey
    private String city;
    private String state_name;
    private String CityState;
    private double longitude;
    private double latitude;
    public RealmList<Temperature>temperature;

    public City() {
    }

    public City( String nameCity, String state, String cityState, double longitude, double latitude, RealmList<Temperature> temperature) {

        this.city = nameCity;
        state_name = state;
        CityState = cityState;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
    }

    public RealmList<Temperature> getTemperature() {
        return temperature;
    }

    public void setTemperature(RealmList<Temperature> temperature) {
        this.temperature = temperature;
    }



    public void setNameCity(String nameCity) {
        this.city = nameCity;
    }

    public void setState(String state) {
        state_name = state;
    }

    public void setCityState(String cityState) {
        CityState = cityState;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public String getNameCity() {
        return city;
    }

    public String getState() {
        return state_name;
    }

    public String getCityState() {
        return CityState;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
