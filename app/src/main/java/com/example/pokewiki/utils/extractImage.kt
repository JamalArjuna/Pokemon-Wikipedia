package com.example.pokewiki.utils

fun extractImageUrlFromPokemonUrl(pokemonUrl: String): Int? {
   val trimmed = pokemonUrl.trimEnd('/')
    return trimmed.substringAfterLast('/').toIntOrNull()
}