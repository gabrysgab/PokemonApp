package com.example.mateusz.pokemonapp.PokeApi;

import com.example.mateusz.pokemonapp.Model.PokemonListResponse;
import com.example.mateusz.pokemonapp.Model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mateusz on 03.04.2017.
 */

public interface PokeApiClient {

    @GET("pokemon/")
    Call<PokemonListResponse> getPokemonList(@Query("limit") Integer limit);

    @GET("pokemon/{id}")
    Call<PokemonResponse> getPokemon(@Path("id") int id);




}
