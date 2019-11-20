package com.example.projetandroidcamelea;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetandroidcamelea.Adapter.PokemonListAdapter;
import com.example.projetandroidcamelea.Common.Common;
import com.example.projetandroidcamelea.Common.ItemOffSetDecoration;
import com.example.projetandroidcamelea.Model.Pokedex;
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

    static PokemonList instance;

    public static PokemonList getInstance() {
        if(instance == null)
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        pokemon_list_recyclerview = (RecyclerView) view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemOffSetDecoration itemOffSetDecoration = new ItemOffSetDecoration(getActivity(),R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffSetDecoration);
        fetchData();
        return view;
    }

    private void fetchData() {
        compositeDisposable.add(ipokemondex.getListPokemon()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Pokedex>(){
            @Override
            public void accept(Pokedex pokedex) throws Exception{
                Common.commonPokemonList = pokedex.getPokemon();
                PokemonListAdapter adapter = new PokemonListAdapter(getActivity(),Common.commonPokemonList);
                pokemon_list_recyclerview.setAdapter(adapter);
            }
        }));
    }

}
