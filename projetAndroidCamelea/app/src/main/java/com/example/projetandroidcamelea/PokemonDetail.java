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
import com.example.projetandroidcamelea.Model.Pokemon;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetail extends Fragment {

    static PokemonDetail instance;
    ImageView pokemon_image;
    TextView pokemon_name,pokemon_height,pokemon_weight;
    RecyclerView  recycler_type,recycler_weakness;

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
        recycler_type =(RecyclerView) itemView.findViewById(R.id.recycler_type);
        recycler_type.setHasFixedSize(true);
        recycler_type.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recycler_weakness =(RecyclerView) itemView.findViewById(R.id.recycler_weakness);
        recycler_weakness.setHasFixedSize(true);
        recycler_weakness.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        setDetailPokemon(pokemon);

        return itemView;

    }

    //Putting the data of pokemon onto the detail fragment
    private void setDetailPokemon(Pokemon pokemon) {
        //Load image
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_image);

        //Loading text data
        pokemon_name.setText((pokemon.getName()));
        pokemon_height.setText("Height : " +pokemon.getHeight());
        pokemon_weight.setText("Weight :"+ pokemon.getWeight());
    }
}
