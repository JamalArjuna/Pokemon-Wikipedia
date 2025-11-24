package com.example.pokewiki.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokewiki.data.models.PokemonItem
import com.example.pokewiki.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val repository: PokemonRepository
): ViewModel() {

    private val listPokemon = mutableListOf<PokemonItem>()

    private val _pokemonList = MutableLiveData<List<PokemonItem>>()
    val pokemonList: LiveData<List<PokemonItem>> = _pokemonList

    fun loadPokemon(){
        viewModelScope.launch {
            try {
                val fetchedPokemonList = repository.getPokemonList()
                listPokemon.clear()
                listPokemon.addAll(fetchedPokemonList)
                _pokemonList.value = listPokemon
                Log.i("Fetch List Pokemon", "loadPokemon: Success ")
            } catch (e: Exception) {
                Log.e("Fetch List Pokemon", "loadPokemon: Failed to fetch pokemon list", e)
            }
        }
    }

    fun searchPokemon(query: String){
        val queryPokemon = query.lowercase()
        val filteredList = if (queryPokemon.isEmpty()){
            listPokemon
        } else {
            listPokemon.filter {
                it.name.contains(queryPokemon)
            }
        }
        _pokemonList.value = filteredList
    }
}