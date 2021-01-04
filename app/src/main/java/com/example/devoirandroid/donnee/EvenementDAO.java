package com.example.devoirandroid.donnee;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.devoirandroid.modele.Evenement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EvenementDAO {

    private static EvenementDAO instance = null;
    //private List<HashMap<String, String>> listeEvenement;
    private List<Evenement> listeEvenement;

    private BaseDeDonnees baseDeDonnees;

    private EvenementDAO(){

        this.baseDeDonnees = baseDeDonnees.getInstance();

        //listeEvenement = new ArrayList<HashMap<String, String>>();
        listeEvenement = new ArrayList<Evenement>();

        //preparerListeEvenement();
    }

    private void preparerListeEvenement(){
        /*
        HashMap<String, String> evenement;

        evenement = new HashMap<String, String>();
        evenement.put("evenement","Fête du travail");
        evenement.put("date","7 septembre 2020" + " 08h00");
        listeEvenement.add(evenement);

        evenement = new HashMap<String, String>();
        evenement.put("evenement","Noël");
        evenement.put("date","25 décembre 2020" + " 00h00");
        listeEvenement.add(evenement);

        evenement = new HashMap<String, String>();
        evenement.put("evenement","Jour de l'an");
        evenement.put("date","31 décembre 2020" + " 00h01");
        listeEvenement.add(evenement);
        */
        listeEvenement.add(new Evenement("Fête du travail", "7 septembre 2020", "08h00", 0));
        listeEvenement.add(new Evenement("Noël", "25 décembre 2020", "00h00", 1));
        listeEvenement.add(new Evenement("Jour de l'an", "31 décembre 2020", "00h01", 2));

    }

    public static EvenementDAO getInstance(){
        if(null == instance){
            instance = new EvenementDAO();
        }
        return instance;
    }

    //public List<Evenement> listerEvenement(){ return listeEvenement; }

    public List<Evenement> listerEvenement(){
        String LISTER_EVENEMENT = "SELECT * FROM evenement" ;
        Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_EVENEMENT,
                null);
        this.listeEvenement.clear();
        Evenement evenement;

        int indexId = curseur.getColumnIndex("id");
        int indexHeure = curseur.getColumnIndex("heure");
        int indexDate = curseur.getColumnIndex("date");
        int indexEvenement = curseur.getColumnIndex("evenement");

        for(curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext()) {
            int id = curseur.getInt(indexId);
            String heure = curseur.getString(indexHeure);
            String date = curseur.getString(indexDate);
            String évenement = curseur.getString(indexEvenement);
            evenement = new Evenement(évenement, date, heure, id);
            this.listeEvenement.add(evenement);
        }

        return listeEvenement;
    }
    /*
    public void ajouterEvenement(HashMap<String, String> evenement){
        //listeEvenement.add(evenement);
    }
    */

    public void ajouterEvenement(Evenement evenement)
    {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();

        baseDeDonneesEcriture.beginTransaction();
        try {

            ContentValues evenementEnCleValeur = new ContentValues();
            evenementEnCleValeur.put("heure", evenement.getHeure());
            evenementEnCleValeur.put("date", evenement.getDate());
            evenementEnCleValeur.put("evenement", evenement.getEvenement());

            baseDeDonneesEcriture.insertOrThrow("evenement", null, evenementEnCleValeur);
            baseDeDonneesEcriture.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("EvenementDAO", "Erreur en tentant d'ajouter un evenement dans la base de données");
        } finally {
            baseDeDonneesEcriture.endTransaction();
        }
    }

    public Evenement chercherEvenementParId(int id){
        listerEvenement();
        for(Evenement evenementRecherche : this.listeEvenement)
        {
            if(evenementRecherche.getId() == id) return evenementRecherche;
        }
        return null;
    }

    public void modifierEvenement(Evenement evenement){
        // TODO : À vous de faire cette méthode --> :)

        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();

        baseDeDonneesEcriture.beginTransaction();
        try {

            ContentValues evenementEnCleValeur = new ContentValues();
            evenementEnCleValeur.put("heure", evenement.getHeure());
            evenementEnCleValeur.put("date", evenement.getDate());
            evenementEnCleValeur.put("evenement", evenement.getEvenement());

            //baseDeDonneesEcriture.insertOrThrow("evenement", null, evenementEnCleValeur);
            baseDeDonneesEcriture.update("evenement", evenementEnCleValeur, "id = "  + evenement.getId(), null);
            baseDeDonneesEcriture.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("EvenementDAO", "Erreur en tentant de modifier un evenement dans la base de données");
        } finally {
            baseDeDonneesEcriture.endTransaction();
        }
    }
}



