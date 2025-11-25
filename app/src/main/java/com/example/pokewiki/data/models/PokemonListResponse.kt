package com.example.pokewiki.data.models

data class PokemonListResponse(
    val count: Int,
    val results: List<PokemonItem> = emptyList()
)