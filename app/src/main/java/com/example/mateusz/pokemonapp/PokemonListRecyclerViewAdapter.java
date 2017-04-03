package com.example.mateusz.pokemonapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mateusz.pokemonapp.Model.PokemonListResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 03.04.2017.
 */

public class PokemonListRecyclerViewAdapter extends RecyclerView.Adapter<PokemonListRecyclerViewAdapter.PokemonListViewHolder> {

    private List<PokemonListResult> pokemonListResults;
    private PokemonListItemClickListener pokemonListItemClickListener;


    public interface PokemonListItemClickListener {

        void onPokemonItemClick(int position);

    }

    public PokemonListRecyclerViewAdapter(PokemonListItemClickListener pokemonListItemClickListener) {
        this.pokemonListItemClickListener = pokemonListItemClickListener;
        this.pokemonListResults = new ArrayList<>();
    }

    public void setPokemonListResults(List<PokemonListResult> pokemonListResults) {
        this.pokemonListResults = pokemonListResults;
        notifyDataSetChanged();
    }

    public PokemonListResult getPokemonListResult(int position) {
        return pokemonListResults.get(position);
    }

    @Override
    public PokemonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_item, parent, false);
        return new PokemonListViewHolder(view, pokemonListItemClickListener);
    }

    @Override
    public void onBindViewHolder(PokemonListViewHolder holder, int position) {
        if (pokemonListResults != null || pokemonListResults.size() != 0) {

            PokemonListResult pokemonListResult = pokemonListResults.get(position);
            holder.name.setText(pokemonListResult.getName());
        }

    }

    @Override
    public int getItemCount() {
        return ((pokemonListResults != null) && (pokemonListResults.size() != 0) ? pokemonListResults.size() : 0);
    }

    static class PokemonListViewHolder extends RecyclerView.ViewHolder {

        TextView name = null;


        public PokemonListViewHolder(View itemView, final PokemonListItemClickListener pokemonListItemClickListener) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.tv_pokemon_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pokemonListItemClickListener != null) {
                        pokemonListItemClickListener.onPokemonItemClick(getAdapterPosition());
                    }
                }
            });


        }
    }


}
