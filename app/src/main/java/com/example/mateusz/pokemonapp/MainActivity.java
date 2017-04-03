package com.example.mateusz.pokemonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mateusz.pokemonapp.Model.PokemonListResponse;
import com.example.mateusz.pokemonapp.Model.PokemonListResult;
import com.example.mateusz.pokemonapp.Model.PokemonResponse;
import com.example.mateusz.pokemonapp.PokeApi.PokeApiClient;
import com.example.mateusz.pokemonapp.PokeApi.PokeApiClientFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PokemonListRecyclerViewAdapter.PokemonListItemClickListener {

    private static final String TAG = "MainActivity";
    PokemonListRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_view);
        setUpViews();




        PokeApiClient pokeApiClient = PokeApiClientFactory.createPokeApiClient();
        Call<PokemonListResponse> call = pokeApiClient.getPokemonList(10);
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {

                int statusCode = response.code();
                Log.d(TAG, "onResponse: response code : " + statusCode);
                PokemonListResponse pokemonListResponse = response.body();
                Log.d(TAG, "onResponse: pokemon list count : " + pokemonListResponse.getPokemonResults().size());
                adapter.setPokemonListResults(pokemonListResponse.getPokemonResults());



            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {

            }

        });
    }

    private void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_pokemon_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PokemonListRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onPokemonItemClick(int position) {
        PokemonListResult pokemonListResult = adapter.getPokemonListResult(position);
        Log.d(TAG, "onPokemonItemClick: " + pokemonListResult.getUrl());
        PokeApiClient pokeApiClient = PokeApiClientFactory.createPokeApiClient();
        Call<PokemonResponse> call = pokeApiClient.getPokemon(position + 1);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                PokemonResponse body = response.body();
                Log.d(TAG, "onResponse: name " + body.getName() + "weight " + body.getWeight());
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

            }
        });
    }
}
