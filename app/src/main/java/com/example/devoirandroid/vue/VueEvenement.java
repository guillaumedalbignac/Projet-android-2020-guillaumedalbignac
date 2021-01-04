package com.example.devoirandroid.vue;

import android.app.TimePickerDialog;
import android.bluetooth.BluetoothHidDeviceAppSdpSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.devoirandroid.R;
import com.example.devoirandroid.donnee.BaseDeDonnees;
import com.example.devoirandroid.donnee.EvenementDAO;
import com.example.devoirandroid.modele.Evenement;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class VueEvenement extends AppCompatActivity {

    protected ListView vueEvenementListeEvenement;
    //protected List<HashMap<String, String>> listeEvenement;
    protected List<Evenement> listeEvenement;
    protected EvenementDAO evenementDAO;

    protected Intent intentionNaviguerAjouterEvenement;
    protected Intent intentionNaviguerModifierEvenement;

    static final public int ACTIVITE_AJOUTER_EVENEMENT=1;
    static final public int ACTIVITE_MODIFIER_EVENEMENT=2;

    TextView mTextViewAlarme;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_evenement);
        vueEvenementListeEvenement = (ListView)findViewById(R.id.vueEvenementListeEvenement);

        //Important que ce getInstance se fasse ici AVANT LivreDAO.getInstance()
        BaseDeDonnees.getInstance(getApplicationContext());

        evenementDAO = EvenementDAO.getInstance();
        /*
        listeEvenement = evenementDAO.listerEvenement();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeEvenement,
                android.R.layout.two_line_list_item,
                new String[]{"evenement","date"},
                new int[]{android.R.id.text1, android.R.id.text2});

        vueEvenementListeEvenement.setAdapter(adapteur);
        */
        afficherListeEvenement();

        Button vueEvenementActionAjouterEvenement = (Button)findViewById(R.id.vueEvenementActionAjouterEvenement);

        intentionNaviguerAjouterEvenement = new Intent(this, VueAjouterEvenement.class);

        vueEvenementActionAjouterEvenement.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO : coder !
                        /*
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action ajouter evenement!",
                                Toast.LENGTH_SHORT);
                        message.show();
                        */
                        //startActivity(intentionNaviguerAjouterEvenement);
                        startActivityForResult(intentionNaviguerAjouterEvenement, ACTIVITE_AJOUTER_EVENEMENT);
                    }
                }
        );

        intentionNaviguerModifierEvenement = new Intent(this, VueModifierEvenement.class);

        vueEvenementListeEvenement.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View vue,
                                            int positionDansAdaptateur, long positionItem){

                        ListView vueListeEvenement = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String,String> evenement =
                                (HashMap<String, String>)
                                        vueListeEvenement.getItemAtPosition((int)positionItem);
                        /*
                        Toast message = Toast.makeText(getApplicationContext(),
                                "Position "+positionItem + " Evenement: "+ evenement.get("evenement"),
                                Toast.LENGTH_SHORT);
                        message.show();
                        */
                        //startActivity(intentionNaviguerModifierEvenement);
                        intentionNaviguerModifierEvenement.putExtra("id", evenement.get("id"));
                        startActivityForResult(intentionNaviguerModifierEvenement, ACTIVITE_MODIFIER_EVENEMENT);
                    }
                }
        );

        mTextViewAlarme = (TextView)findViewById(R.id.vueEvenementActionAjouterAlarme);

        Calendar dateActuel = Calendar.getInstance();

        final int heures = dateActuel.get(Calendar.HOUR_OF_DAY);
        final int minutes = dateActuel.get(Calendar.MINUTE);

        Button vueEvenementActionAjouterAlarme = (Button)findViewById(R.id.vueEvenementActionAjouterAlarme);
        vueEvenementActionAjouterAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Calendar dateActuel = Calendar.getInstance();

                final int heures = dateActuel.get(Calendar.HOUR_OF_DAY);
                final int minutes = dateActuel.get(Calendar.MINUTE);

                Intent activerAlarme = new Intent(AlarmClock.ACTION_SET_ALARM);
                activerAlarme.putExtra(AlarmClock.EXTRA_HOUR, heures);
                activerAlarme.putExtra(AlarmClock.EXTRA_MINUTES, minutes);

                if(heures <= 24 && minutes <= 60) {
                    startActivity(activerAlarme);
                }*/

               TimePickerDialog alarme = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int heures, int minutes) {
                        mTextViewAlarme.setText(heures + " " + minutes);

                       Intent activerAlarme = new Intent(AlarmClock.ACTION_SET_ALARM);
                       activerAlarme.putExtra(AlarmClock.EXTRA_HOUR, heures);
                       activerAlarme.putExtra(AlarmClock.EXTRA_MINUTES, minutes);

                       if(heures <= 24 && minutes <= 60) {
                           startActivity(activerAlarme);
                       }

                   }
               },heures,minutes, android.text.format.DateFormat.is24HourFormat(mContext));
               alarme.show();
            }
        });
    }

    protected void onActivityResult(int activite, int resultat, Intent donnees){
        super.onActivityResult(activite, resultat, donnees);
        switch (activite){
            case ACTIVITE_AJOUTER_EVENEMENT:
                afficherListeEvenement();
                break;
            case ACTIVITE_MODIFIER_EVENEMENT:
                afficherListeEvenement();
                break;
        }
    }

    public void afficherListeEvenement(){
        listeEvenement = evenementDAO.listerEvenement();

        List<HashMap<String, String>> listeEvenementPourAfficher =
                new ArrayList<HashMap<String, String>>();

        for(Evenement evenement:listeEvenement){
            listeEvenementPourAfficher.add(evenement.obtenirEvenementPourAfficher());
        }

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeEvenementPourAfficher,
                android.R.layout.two_line_list_item,
                new String[]{"evenement","date"},
                new int[]{android.R.id.text1, android.R.id.text2});

        vueEvenementListeEvenement.setAdapter(adapteur);
    }

    /*public List<HashMap<String, String>> preparerListeEvenement(){
        List<HashMap<String, String>> listeEvenement = new ArrayList<HashMap<String, String>>();

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

        return listeEvenement;
    }*/
}