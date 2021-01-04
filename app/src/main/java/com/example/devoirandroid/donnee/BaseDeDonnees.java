package com.example.devoirandroid.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static synchronized BaseDeDonnees getInstance(Context contexte)
    {
        instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "evenement", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table evenement(id INTEGER PRIMARY KEY, evenement TEXT, date TEXT, heure TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        /*
        // La première fois ça créer l'échafaud, à la seconde exécution, il faut commenter l'intérieur de cette méthode.

        String DELETE = "delete from evenement where 1 = 1";
        db.execSQL(DELETE);

        // ...
        String INSERT_1 = "insert into evenement(evenement, date, heure) VALUES('Acheter du lait', '15 septembre ', '15h')";
        String INSERT_2 = "insert into evenement(evenement, date, heure) VALUES('Recupérer fournitures scolaires', '27 septembre', '9h30')";
        String INSERT_3 = "insert into evenement(evenement, date, heure) VALUES('Ranger le garage', 'dès que possible', 'au choix')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        //String DETRUIRE_TABLE = "drop table livre";
        //db.execSQL(DETRUIRE_TABLE);
        String CREER_TABLE = "create table evenement(id INTEGER PRIMARY KEY, evenement TEXT, date TEXT, heure TEXT)";
        db.execSQL(CREER_TABLE);
    }
}
