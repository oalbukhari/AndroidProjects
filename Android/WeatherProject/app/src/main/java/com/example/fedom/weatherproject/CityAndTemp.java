package com.example.fedom.weatherproject;

/**
 * Created by fedom on 22/02/2017.
 */

public class CityAndTemp {

    private String NomeCitta;
    private String Temperatura;
    private String DataInseriemnto;

    public CityAndTemp(String City, String Temp, String Date){
        this.NomeCitta=City.toUpperCase();
        this.Temperatura=Temp.toUpperCase();
        this.DataInseriemnto=Date.toUpperCase();

    }

    public void setDataInseriemnto(String dataInseriemnto) {
        DataInseriemnto = dataInseriemnto;
    }

    public void setNomeCitta(String nomeCitta) {
        NomeCitta = nomeCitta;
    }

    public void setTemperatura(String temperatura) {
        Temperatura = temperatura;
    }

    public String getDataInseriemnto() {
        return DataInseriemnto;
    }

    public String getNomeCitta() {
        return NomeCitta;
    }

    public String getTemperatura() {
        return Temperatura;
    }
}
