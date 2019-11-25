package com.example.projetandroidcamelea.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projetandroidcamelea.Model.Pokemon;
import com.example.projetandroidcamelea.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    Context context;
    List<Pokemon> pokemonList;
    public String affichage;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (affichage == "grid") {
            View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false);
            return new MyViewHolder(itemView);
        }
        else {
            View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_diplay_item, parent, false);
            return new MyViewHolder(itemView);

        }
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Charger l'image
        Glide.with(context).load(pokemonList.get(position).getImg()).into(holder.pokemon_image);
        //Modifier le nom
        holder.pokemon_name.setText(pokemonList.get(position).getName());




    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemon_image;
        TextView pokemon_name;

        public MyViewHolder(View itemView){
            super(itemView);

            pokemon_image = (ImageView)itemView.findViewById(R.id.pokemon_image);
            pokemon_name = (TextView)itemView.findViewById(R.id.pokemon_name);

        }

    }
}
