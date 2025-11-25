package com.example.pokewiki.data.models

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<TypeSlot>,
    val stats: List<stats>
)

data class Sprites(
    @SerializedName("front_default")
    val front_default: String?
)
data class TypeSlot(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
)

data class stats(
    val base_stat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String,
)
