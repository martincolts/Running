package com.example.tin.running.JavaClases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class StatsSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Stats (fecha TEXT, distancia TEXT, velMax TEXT, velPromedio TEXT, tiempo TEXT)";

    public StatsSQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creacion de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Se elimina la version anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Stats");

        //Se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
}