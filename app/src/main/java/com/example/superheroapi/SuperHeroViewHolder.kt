package com.example.superheroapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.databinding.ItemSuperherolistBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperherolistBinding.bind(view)

    fun bind(superHeroResults: SuperHeroResults, onItemSelected: (String) -> Unit) {
        binding.tvSuperheroName.text = superHeroResults.name
        Picasso.get().load(superHeroResults.image.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener {
            onItemSelected(superHeroResults.id)
        }
    }
}