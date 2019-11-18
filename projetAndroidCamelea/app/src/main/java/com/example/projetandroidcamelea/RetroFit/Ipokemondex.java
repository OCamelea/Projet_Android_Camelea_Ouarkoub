package com.example.projetandroidcamelea.RetroFit;

import android.database.Observable;

import com.example.projetandroidcamelea.Model.Pokedex;

import retrofit2.http.GET;

public interface Ipokemondex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
