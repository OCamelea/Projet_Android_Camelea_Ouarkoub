package com.example.projetandroidcamelea;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.projetandroidcamelea.Adapter.PokemonListAdapter;
import com.example.projetandroidcamelea.Common.Common;
import com.example.projetandroidcamelea.Common.ItemOffSetDecoration;
import com.example.projetandroidcamelea.DataBase.PokemonDAO;
import com.example.projetandroidcamelea.Model.Pokedex;
import com.example.projetandroidcamelea.Model.Pokemon;
import com.example.projetandroidcamelea.RetroFit.Ipokemondex;
import com.example.projetandroidcamelea.RetroFit.RetroFitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonList extends Fragment {
    Ipokemondex ipokemondex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;
    private ImageView list_icon;
    private ImageView grid_icon;
    private String affichage = "list";
    private RecyclerView.LayoutManager layoutManager;
    private PokemonDAO database;

    static PokemonList instance;

    public static PokemonList getInstance() {
        if (instance == null)
            instance = new PokemonList();
        return instance;
    }

    public PokemonList() {
        Retrofit retrofit = RetroFitClient.getInstace();
        ipokemondex = retrofit.create(Ipokemondex.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        database = PokemonDAO.getInstance(getActivity());


        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        // Inflate the layout for this fragment
        list_icon = (ImageView) view.findViewById(R.id.list_icon);
        grid_icon = (ImageView) view.findViewById(R.id.grid_icon);

        layoutManager = new LinearLayoutManager(getActivity());

        //Setting the layout
        pokemon_list_recyclerview = (RecyclerView) view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setLayoutManager(layoutManager);
        pokemon_list_recyclerview.setHasFixedSize(true);
        //Fetching the data
        fetchData();
        //Handling the display mode when clicking on the list button
        list_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutManager = new LinearLayoutManager(getActivity());
                pokemon_list_recyclerview.setLayoutManager(layoutManager);
                affichage = "list";
                fetchData();
            }
        });
        //Handling the display mode when clicking on the grid button
        grid_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutManager = new GridLayoutManager(getActivity(), 2);
                pokemon_list_recyclerview.setLayoutManager(layoutManager);
                affichage = "grid";
                fetchData();
            }
        });
        ItemOffSetDecoration itemOffSetDecoration = new ItemOffSetDecoration(getActivity(), R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffSetDecoration);
        fetchData();
        return view;
    }

    private void fetchData() {
        //If the data are already loaded on the data base we fetch them from it
        if (database.getAllPokemons().size() == 151) {
            Common.commonPokemonList = database.getAllPokemons();
            //New adapter
            PokemonListAdapter adapter = new PokemonListAdapter(getActivity(), Common.commonPokemonList);
            adapter.affichage = affichage;
            pokemon_list_recyclerview.setAdapter(adapter);

            //Else we fetch the data from the api
        } else {
            compositeDisposable.add(ipokemondex.getListPokemon()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Pokedex>() {
                                   @Override
                                   public void accept(Pokedex pokedex) throws Exception {
                                       Common.commonPokemonList = pokedex.getPokemon();
                                       //Putting the data into the database.
                                       if (database.getAllPokemons().size() < 151) {
                                           database.PutPokemonList(pokedex.getPokemon());
                                       }
                                       //New adapter
                                       PokemonListAdapter adapter = new PokemonListAdapter(getActivity(), Common.commonPokemonList);
                                       adapter.affichage = affichage;
                                       pokemon_list_recyclerview.setAdapter(adapter);

                                   }

                               }
                    ));
        }
    }
}


