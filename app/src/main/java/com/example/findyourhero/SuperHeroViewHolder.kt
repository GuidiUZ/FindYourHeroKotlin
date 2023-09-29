package com.example.findyourhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.findyourhero.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view : View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind (superHeroItemResponse : SuperHeroItemResponse, onItemSelect: (String) -> Unit) {
        binding.tvSuperHeroName.text = superHeroItemResponse.superHeroName
        Picasso.get().load(superHeroItemResponse.superHeroImage.url).into(binding.imageSuperHero)
        binding.root.setOnClickListener { onItemSelect(superHeroItemResponse.superHeroId) }
    }
}