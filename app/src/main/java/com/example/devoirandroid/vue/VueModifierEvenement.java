package com.example.devoirandroid.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.devoirandroid.R;
import com.example.devoirandroid.donnee.EvenementDAO;
import com.example.devoirandroid.modele.Evenement;

public class VueModifierEvenement extends AppCompatActivity {

    protected EditText vueModifierEvenementChampEvenement;
    protected EditText vueModifierEvenementChampDate;
    protected EditText vueModifierEvenementChampHeure;
    protected EvenementDAO evenementDAO;
    protected Evenement evenement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_evenement);

        Button vueModifierEvenementActionAnnuler = (Button)findViewById(R.id.vueModifierEvenementActionAnnuler);

        vueModifierEvenementActionAnnuler.setOnClickListener(
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

        Bundle parametres = this.getIntent().getExtras();
        String idParametre = (String) parametres.get("id");
        int id = Integer.parseInt(idParametre);
        evenementDAO = EvenementDAO.getInstance();
        evenement = evenementDAO.chercherEvenementParId(id);

        vueModifierEvenementChampEvenement = (EditText)findViewById(R.id.vueModifierEvenementChampEvenement);
        vueModifierEvenementChampDate = (EditText)findViewById(R.id.vueModifierEvenementChampDate);
        vueModifierEvenementChampHeure = (EditText)findViewById(R.id.vueModifierEvenementChampHeure);
        vueModifierEvenementChampEvenement.setText(evenement.getEvenement());
        vueModifierEvenementChampDate.setText(evenement.getDate());
        vueModifierEvenementChampHeure.setText(evenement.getHeure());

        Button vueModifierEvenementActionModifier = (Button)findViewById(R.id.vueModifierEvenementActionModifier);

        vueModifierEvenementActionModifier.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        // TODO : coder!
                        /*
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Champ titre:"+vueModifierLivreChampTitre.getText().toString()+
                                        " Champ auteur:"+vueModifierLivreChampAuteur.getText().toString(),
                                Toast.LENGTH_SHORT);
                        message.show();

                         */

                        enregisterEvenement();
                        naviguerRetourEvenements();
                    }
                }
        );

    }

    private void enregisterEvenement() {

        evenement.setHeure(vueModifierEvenementChampHeure.getText().toString());
        evenement.setDate(vueModifierEvenementChampDate.getText().toString());
        evenement.setEvenement(vueModifierEvenementChampEvenement.getText().toString());

        evenementDAO.modifierEvenement(evenement);
    }

    public void naviguerRetourEvenements(){
        this.finish();
    }
}