package com.example.projetandroidcamelea.RetroFit;

import io.reactivex.Observable;

import com.example.projetandroidcamelea.Model.Pokedex;

import retrofit2.http.GET;

public interface Ipokemondex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
