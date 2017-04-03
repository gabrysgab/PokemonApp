package com.example.mateusz.pokemonapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 03.04.2017.
 */

public class PokemonListResponse {


    private int count;
    private String previous;
    @SerializedName("results")
    private List<PokemonListResult> pokemonResults = new ArrayList<>();
    private String next;


    public int getCount() {
        return count;
    }

    public String getPrevious() {
        return previous;
    }

    public List<PokemonListResult> getPokemonResults() {
        return pokemonResults;
    }

    public String getNext() {
        return next;
    }
}
