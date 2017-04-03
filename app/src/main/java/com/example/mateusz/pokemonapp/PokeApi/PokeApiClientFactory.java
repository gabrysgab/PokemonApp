package com.example.mateusz.pokemonapp.PokeApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mateusz on 03.04.2017.
 */

public class PokeApiClientFactory {

    private final static String BASE_URL = "http://pokeapi.co/api/v2/";

    public static PokeApiClient createPokeApiClient() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        return retrofit.create(PokeApiClient.class);


    }


}
