package com.example.projetandroidcamelea.Model;

import java.util.List;

public class Pokedex
{
    private List<Pokemon> pokemon;


    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public Pokedex(List<Pokemon>pokemon){
        this.pokemon=pokemon;

    }


}