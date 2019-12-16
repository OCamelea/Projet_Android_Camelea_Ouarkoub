package com.example.projetandroidcamelea.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetandroidcamelea.Model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {
    private SQLiteDatabase database;
    private PokemonHelper dbHelper;
    private static PokemonDAO instance;

    private String[] TableColumns = {
            PokemonHelper.POKEMON_COLUMN_ID,
            PokemonHelper.POKEMON_COLUMN_NAME,
            PokemonHelper.POKEMON_COLUMN_IMAGE,
 };

    private PokemonDAO(Context context) {
        dbHelper = PokemonHelper.getInstance(context);
    }

    public static PokemonDAO getInstance(Context context) {
        if (instance == null) {
            instance = new PokemonDAO(context);
        }
        return instance;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
        dbHelper.close();
    }

    public boolean PutPokemonList(List<Pokemon> pokemons) {
        for (Pokemon pokemon : pokemons) {
            if (!this.PutPokemon(pokemon))
                return false;
        }
        return true;
    }

    public boolean PutPokemon(Pokemon pokemon) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.POKEMON_COLUMN_NAME, pokemon.getName());
        values.put(dbHelper.POKEMON_COLUMN_IMAGE, pokemon.getImg());
        long rowId = database.insert(dbHelper.POKEMON_TABLE_NAME, null, values);
        if (rowId == -1){
            return false;
    }
        return true;
    }

    public List<Pokemon> getAllPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + dbHelper.POKEMON_TABLE_NAME, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                pokemons.add(new Pokemon(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return pokemons;
    }

}
