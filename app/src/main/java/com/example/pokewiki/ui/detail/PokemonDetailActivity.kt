package com.example.pokewiki.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.pokewiki.R
import coil.load
import com.example.pokewiki.databinding.ActivityPokemonDetailBinding
import com.example.pokewiki.viewmodel.PokemonDetailViewModel

class PokemonDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_POKEMON_NAME = "pokemon_name"
    }
    lateinit var binding: ActivityPokemonDetailBinding
    private val viewModel: PokemonDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonName = intent.getStringExtra(EXTRA_POKEMON_NAME)
        if (pokemonName.isNullOrEmpty()){
            finish() // Close the activity if no Pokémon name is provided
            return
        }

        observeViewModel()
        viewModel.loadDetail(pokemonName)

    }

    private fun observeViewModel() {
        viewModel.detail.observe(this) { detail ->
            binding.tvName.text = detail.name

            val tipePokemon = detail.types.joinToString(" · ") {
                it.type.name
            }

            binding.tvTypes.text = tipePokemon

            binding.ivSprite.load(detail.sprites.front_default) {
                crossfade(true)
            }
        }
    }
}
