package com.example.pokewiki.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokewiki.data.models.PokemonDetail
import com.example.pokewiki.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _detailPokemon = MutableLiveData<PokemonDetail>()
    val detail: LiveData<PokemonDetail> = _detailPokemon

    fun loadDetail(name: String) {
        viewModelScope.launch {
            try {
                _detailPokemon.value = repository.getPokemonDetail(name)
            } catch (e: Exception) {
                Log.e("Error Fetching Data", "loadDetail: Error to Get detail Pokemon $name", )
            }
        }
    }

}