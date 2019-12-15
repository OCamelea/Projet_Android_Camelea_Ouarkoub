package com.example.projetandroidcamelea;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projetandroidcamelea.Common.Common;
import com.example.projetandroidcamelea.Model.NextEvolution;
import com.example.projetandroidcamelea.Model.Pokemon;
import com.example.projetandroidcamelea.Model.PrevEvolution;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetail extends Fragment {

    static PokemonDetail instance;
    ImageView pokemon_image;
    TextView pokemon_name,pokemon_height,pokemon_weight, pokemon_weaknesses,pokemon_type, pokemon_next_evol , pokemon_previous_evol;


    public static PokemonDetail getInstance() {
        if (instance == null){
            instance= new PokemonDetail();
        }
            return instance;

    }


    public PokemonDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        Pokemon pokemon;
        //Get position
        if(getArguments().get("number") == null){
            pokemon = Common.commonPokemonList.get(getArguments().getInt("position"));

        }
        else
            pokemon =null;
        pokemon_image = (ImageView)itemView.findViewById(R.id.pokemon_image);
        pokemon_name = (TextView) itemView.findViewById(R.id.pokemon_name);
        pokemon_height = (TextView) itemView.findViewById(R.id.height);
        pokemon_weight = (TextView) itemView.findViewById(R.id.weight);
        pokemon_type = (TextView) itemView.findViewById(R.id.pokemon_type);
        pokemon_weaknesses = (TextView) itemView.findViewById(R.id.pokemon_weakness);
        pokemon_previous_evol = (TextView) itemView.findViewById(R.id.pokemon_previous_evolution);
        pokemon_next_evol = (TextView) itemView.findViewById(R.id.pokemon_next_evolution);

        setDetailPokemon(pokemon);

        return itemView;

    }

    //Putting the data of pokemon onto the detail fragment
    private void setDetailPokemon(Pokemon pokemon) {
        //Load image
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_image);

        //Loading text data
        pokemon_name.setText((pokemon.getName()));
        pokemon_height.setText("Height  "  + "\t" +pokemon.getHeight());
        pokemon_weight.setText("Weight " + "\t" +pokemon.getWeight());

        //Converting list of types to string
        String pokemon_types = "Types :" + "\t";
        for (String s : pokemon.getType())
        {
            pokemon_types += s + " ";
        }
        pokemon_type.setText(pokemon_types);

        //Converting list of weakness to string
        String pokemon_weakness = "Weakness :" + "\t";
        for (String s : pokemon.getWeaknesses())
        {
            pokemon_weakness += s + " ";
        }
        pokemon_weaknesses.setText(pokemon_weakness);

        //Converting list of next evolutions to string
        if(pokemon.getNext_evolution() != null) {
            String pokemon_next_evolution = "Next evolutions :" + "\t";
            for (NextEvolution s : pokemon.getNext_evolution()) {
                pokemon_next_evolution += s.getName() + "\t";
            }
            pokemon_next_evol.setText(pokemon_next_evolution);
        }
        //Converting list of previous evolutions to string
        if(pokemon.getPrev_evolution() != null){
        String pokemon_previous_evolution = "Previous evolutions :" + "\t";
        for (PrevEvolution s : pokemon.getPrev_evolution())
        {
            pokemon_previous_evolution += s.getName() + "\t";
        }
        pokemon_previous_evol.setText(pokemon_previous_evolution);

    }}
}
