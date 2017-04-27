package com.example.fedom.beforeexam;

/**
 * Created by fedom on 21/02/2017.
 */

public class Person {

    String nome ;
    String cognome;
    String eta;

    public Person (String nome, String cognome, String eta) {
        this.nome=nome;
        this.cognome=cognome;
        this.eta=eta;


    }

    public String getCognome() {
        return cognome;
    }

    public String getEta() {
        return eta;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public void setNome(String name) {
        this.nome = name;
    }
}
