package com.example.devoirandroid.modele;

import java.util.HashMap;

public class Evenement {
    protected String evenement;
    protected String date;
    protected String heure;
    protected int id;

    public Evenement(String evenement, String date, String heure, int id) {
        this.evenement = evenement;
        this.date = date;
        this.heure = heure;
        this.id = id;
    }

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, String> obtenirEvenementPourAfficher(){
        HashMap<String, String> evenementPourAfficher = new HashMap<String, String>();
        evenementPourAfficher.put("evenement", this.evenement);
        evenementPourAfficher.put("date", this.date + " " + this.heure);
        evenementPourAfficher.put("id", "" + this.id);
        return evenementPourAfficher;
    }
}
