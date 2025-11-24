package com.example.pokewiki.data.api

import com.example.pokewiki.data.models.PokemonDetail
import com.example.pokewiki.data.models.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPIService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query ("limit")
        Limit: Int = 1000,
        @Query ("offset")
        Offset: Int = 0
    ): PokemonListResponse

    @GET ("pokemon/{namaPokemon}")
    suspend fun getPokemonDetail(
        @Path ("namaPokemon") name: String
    ): PokemonDetail

}