package com.example.pokewiki.ui

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.postDelayed
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokewiki.R
import com.example.pokewiki.data.models.PokemonItem
import com.example.pokewiki.databinding.ActivityMainBinding
import com.example.pokewiki.ui.aboutapp.AboutAppActivity
import com.example.pokewiki.ui.aboutme.AboutMeActivity
import com.example.pokewiki.ui.detail.PokemonAdapter
import com.example.pokewiki.ui.detail.PokemonDetailActivity
import com.example.pokewiki.viewmodel.PokemonListViewModel
import kotlin.getValue
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PokemonAdapter { pokemonItem ->
            openDetail(pokemonItem)
        }
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvPokemon.adapter = adapter

        observeViewModel()
        setupSearch()
        viewModel.loadPokemon()

        // Opening Side bar
        binding.bottomAppbarInclude.fabMenuSidebar.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)){
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            } else{
                    binding.drawerLayout.openDrawer(GravityCompat.END)
            }
        }

        binding.bottomAppbarInclude.fabProfile.setOnClickListener {
            var intentAboutMe = Intent(this, AboutMeActivity::class.java)
            startActivity(intentAboutMe)
        }

        // Navigation Item Selected Listener
        binding.navView.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.about_app -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.END)
                    binding.drawerLayout.postDelayed( // Add a slight delay to allow the drawer to close smoothly
                        {
                            var intentAboutApp = Intent(this, AboutAppActivity::class.java)
                            startActivity(intentAboutApp)
                        }, 200
                    )
                    return@setNavigationItemSelectedListener true
                }
                R.id.list_pokemon -> {
                    if (true){
                        binding.drawerLayout.closeDrawer(GravityCompat.END)
                        return@setNavigationItemSelectedListener true
                    } else{
                        binding.drawerLayout.closeDrawer(GravityCompat.END)
                        binding.drawerLayout.postDelayed( // Add a slight delay to allow the drawer to close smoothly
                            {
                                var intentHome = Intent(this, MainActivity::class.java)
                                startActivity(intentHome)
                            }, 200
                        )
                        return@setNavigationItemSelectedListener true
                    }

                }
            }
            false
        }
    }

    private fun observeViewModel() {
        viewModel.pokemonList.observe(this) { pokemonList ->
            Log.d("UI", "observeViewModel: list size = ${pokemonList.size}")
            adapter.submitList(pokemonList)
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchPokemon(s?.toString().orEmpty())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun openDetail(pokemonItem: PokemonItem) {
        val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON_NAME, pokemonItem.name)
        startActivity(intent)
    }

    private fun hideKeyboard() {
        currentFocus?.let { view ->
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    hideKeyboard()
                    v.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }


}