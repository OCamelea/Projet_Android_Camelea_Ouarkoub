package com.example.projetandroidcamelea.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetandroidcamelea.Model.NextEvolution;
import com.example.projetandroidcamelea.Model.Pokemon;
import com.example.projetandroidcamelea.Model.PrevEvolution;

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
            PokemonHelper.POKEMON_COLUMN_HEIGHT,
            PokemonHelper.POKEMON_COLUMN_WEIGHT,
            PokemonHelper.POKEMON_COLUMN_TYPES,
            PokemonHelper.POKEMON_COLUMN_WEAKNESS,
            PokemonHelper.POKEMON_COLUMN_NEXT_EVOL,
            PokemonHelper.POKEMON_COLUMN_PREV_EVOL,

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
        values.put(dbHelper.POKEMON_COLUMN_HEIGHT, pokemon.getHeight());
        values.put(dbHelper.POKEMON_COLUMN_WEIGHT, pokemon.getWeight());
        String typesList = "";
        if(pokemon.getType() != null) {
            for (String t : pokemon.getType()) {
                typesList = t + "";
            }
        }
        values.put(dbHelper.POKEMON_COLUMN_TYPES, typesList);

        String weaknesses = "";
        for(String  t : pokemon.getWeaknesses()){
            weaknesses = t + "";
        }
        values.put(dbHelper.POKEMON_COLUMN_WEAKNESS, weaknesses);

        String nextEvolutions = "";
        if(pokemon.getNext_evolution() != null ) {
            for (NextEvolution ne : pokemon.getNext_evolution()) {
                nextEvolutions = ne.getName() + "";
            }
        }
        values.put(dbHelper.POKEMON_COLUMN_NEXT_EVOL, nextEvolutions);

        String previousEvolutions = "";
        if(pokemon.getPrev_evolution() != null ) {
            for (PrevEvolution pe : pokemon.getPrev_evolution()) {
                previousEvolutions = pe.getName() + "";
            }
        }
        values.put(dbHelper.POKEMON_COLUMN_PREV_EVOL, previousEvolutions);

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
                pokemons.add(new Pokemon(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return pokemons;
    }

}
