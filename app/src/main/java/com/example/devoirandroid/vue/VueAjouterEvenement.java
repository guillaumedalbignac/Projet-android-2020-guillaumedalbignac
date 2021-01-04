package com.example.devoirandroid.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.devoirandroid.R;
import com.example.devoirandroid.donnee.EvenementDAO;
import com.example.devoirandroid.modele.Evenement;

import java.util.Date;
import java.util.HashMap;

public class VueAjouterEvenement extends AppCompatActivity {

    protected EditText vueAjouterEvenementChampEvenement;
    protected EditText vueAjouterEvenementChampDate;
    protected EditText vueAjouterEvenementChampHeure;

    protected Date vueAjouterEvenementChampDate2;

    protected EvenementDAO evenementDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_evenement);

        Button vueAjouterEvenementActionAnnuler = (Button)findViewById(R.id.vueAjouterEvenementActionAnnuler);

        vueAjouterEvenementActionAnnuler.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO : coder !
                        /*
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action annuler!",
                                Toast.LENGTH_SHORT);
                        message.show();
                        */
                        naviguerRetourEvenements();
                    }
                }
        );

        vueAjouterEvenementChampEvenement = (EditText)findViewById(R.id.vueAjouterEvenementChampEvenement);
        vueAjouterEvenementChampDate = (EditText)findViewById(R.id.vueAjouterEvenementChampDate);
        vueAjouterEvenementChampHeure = (EditText)findViewById(R.id.vueAjouterEvenementChampHeure);

        Button vueAjouterEvenementActionAjouter = (Button)findViewById(R.id.vueAjouterEvenementActionAjouter);

        vueAjouterEvenementActionAjouter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO : coder !
                        /*
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Champ evenement " + vueAjouterEvenementChampEvenement.getText().toString() +
                                        "Champ Date " + vueAjouterEvenementChampDate.getText().toString() +
                                        "Champ heure " + vueAjouterEvenementChampHeure.getText().toString(),
                                Toast.LENGTH_SHORT);
                        message.show();
                         */
                        enregistrerEvenement();
                        naviguerRetourEvenements();
                    }
                }
        );

    }

    private void enregistrerEvenement(){
        /*
        HashMap<String, String> evenement;

        evenement = new HashMap<String, String>();
        evenement.put("evenement", vueAjouterEvenementChampEvenement.getText().toString());
        evenement.put("date", vueAjouterEvenementChampDate.getText().toString()+ " " + vueAjouterEvenementChampHeure.getText().toString());

        evenementDAO = EvenementDAO.getInstance();
        evenementDAO.ajouterEvenement(evenement);
         */

        Evenement evenement = new Evenement(vueAjouterEvenementChampEvenement.getText().toString(),
                vueAjouterEvenementChampDate.getText().toString(), vueAjouterEvenementChampHeure.getText().toString(),0);

        evenementDAO = EvenementDAO.getInstance();

        evenementDAO.ajouterEvenement(evenement);
    }

    public void naviguerRetourEvenements(){
        this.finish();
    }
}