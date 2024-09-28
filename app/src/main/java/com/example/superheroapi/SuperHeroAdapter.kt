package com.example.superheroapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(
    var superherolist: List<PokemonResults> = emptyList(),  // Usamos la lista de resultados de Pokémon
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<SuperHeroViewHolder>() {

    // Actualizar la lista de Pokémon
    fun updateList(superherolist: List<PokemonResults>) {
        this.superherolist = superherolist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superherolist, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return superherolist.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superherolist[position], onItemSelected)
    }
}