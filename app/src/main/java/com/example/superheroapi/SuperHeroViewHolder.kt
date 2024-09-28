package com.example.superheroapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.databinding.ItemSuperherolistBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperherolistBinding.bind(view)

    fun bind(pokemonResults: PokemonResults, onItemSelected: (String) -> Unit) {
        // Mostrar el nombre del Pokémon en el TextView
        binding.tvSuperheroName.text = pokemonResults.name

        // Cargar la imagen del Pokémon con Picasso
        Picasso.get().load(pokemonResults.image).into(binding.ivSuperhero)

        // Configurar el click listener, pasando el nombre del Pokémon
        binding.root.setOnClickListener {
            onItemSelected(pokemonResults.name)  // También puedes usar id si es necesario
        }
    }
}