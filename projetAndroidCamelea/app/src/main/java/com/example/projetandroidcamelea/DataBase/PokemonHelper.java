package com.example.projetandroidcamelea.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PokemonHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pokemon_database";
    public static final String POKEMON_TABLE_NAME = "pokemon_table";
    public static final String POKEMON_COLUMN_ID = "_id";
    public static final String POKEMON_COLUMN_NAME = "name";
    public static final String POKEMON_COLUMN_IMAGE = "image";

    public PokemonHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating the table
        db.execSQL("CREATE TABLE " + POKEMON_TABLE_NAME + " (" +
                POKEMON_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                POKEMON_COLUMN_NAME + " TEXT, " +
                POKEMON_COLUMN_IMAGE + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + POKEMON_TABLE_NAME);
        onCreate(db);

    }
}
