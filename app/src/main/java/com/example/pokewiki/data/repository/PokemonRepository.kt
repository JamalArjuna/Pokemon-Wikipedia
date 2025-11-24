package com.example.pokewiki.data.repository

import com.example.pokewiki.data.api.PokeApiClient
import com.example.pokewiki.data.models.PokemonDetail
import com.example.pokewiki.data.models.PokemonItem

class PokemonRepository {

    suspend fun getPokemonList(): List<PokemonItem>{
        return PokeApiClient.apiService.getPokemonList().list
    }

    suspend fun getPokemonDetail(name: String): PokemonDetail{
        return PokeApiClient.apiService.getPokemonDetail(name)
    }
}