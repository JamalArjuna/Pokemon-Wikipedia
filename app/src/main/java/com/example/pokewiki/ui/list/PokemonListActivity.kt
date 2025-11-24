package com.example.pokewiki.ui.list

import  android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pokewiki.R
import com.example.pokewiki.data.models.PokemonItem
import com.example.pokewiki.databinding.ActivityPokemonListBinding
import com.example.pokewiki.ui.detail.PokemonAdapter
import com.example.pokewiki.ui.detail.PokemonDetailActivity
import com.example.pokewiki.viewmodel.PokemonListViewModel

class PokemonListActivity : AppCompatActivity() {

    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var binding: ActivityPokemonListBinding
    lateinit var etSearch : EditText
    lateinit var rvPokemonList : RecyclerView
    private lateinit var adapter: PokemonAdapter
    fun initComponents(){
        etSearch = findViewById<EditText>(R.id.et_search)
        rvPokemonList = findViewById<RecyclerView>(R.id.rv_pokemon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()

        adapter = PokemonAdapter { pokemonItem ->
            openDetail(pokemonItem)
        }
        rvPokemonList.adapter = adapter
        observeViewModel()
        setupSearch()

        viewModel.loadPokemon()

    }
    private fun observeViewModel(){
        viewModel.pokemonList.observe(this) { pokemonList ->
            adapter.submitList(pokemonList)
        }
    }

    private fun setupSearch(){
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun openDetail(pokemonItem: PokemonItem){
        val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON_NAME, pokemonItem.name)
        startActivity(intent)
    }
}




