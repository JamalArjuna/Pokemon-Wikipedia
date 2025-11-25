package com.example.pokewiki.data.repository

import android.util.Log
import com.example.pokewiki.data.api.PokeApiClient
import com.example.pokewiki.data.models.PokemonDetail
import com.example.pokewiki.data.models.PokemonItem

class PokemonRepository {

    suspend fun getPokemonList(): List<PokemonItem>{
        val respon =  PokeApiClient.apiService.getPokemonList()
        Log.d("Repo", "getPokemonList: results size = ${respon.results.size}")
        return respon.results
    }

    suspend fun getPokemonDetail(name: String): PokemonDetail{
        return PokeApiClient.apiService.getPokemonDetail(name)
    }
}