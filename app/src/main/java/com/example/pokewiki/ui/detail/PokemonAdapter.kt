package com.example.pokewiki.ui.detail

import com.example.pokewiki.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokewiki.data.models.PokemonItem

class PokemonAdapter(private val onClick: (PokemonItem) -> Unit): ListAdapter<PokemonItem, PokemonAdapter.ViewHolderPokemon>(DIFF) {
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<PokemonItem>() {
            override fun areItemsTheSame(oldItem: PokemonItem, newItem: PokemonItem) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PokemonItem, newItem: PokemonItem) =
                oldItem == newItem
        }
    }

    inner class ViewHolderPokemon(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(pokemonItem: PokemonItem){
            val tvName = itemView.findViewById<TextView>(R.id.tv_name_pokemon)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPokemon {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return ViewHolderPokemon(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPokemon, position: Int) {
        holder.bind(getItem(position))
    }
}
