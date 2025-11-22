package com.example.pokewiki.ui.aboutapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.pokewiki.R
import com.example.pokewiki.databinding.ActivityAboutAppBinding
import com.example.pokewiki.ui.MainActivity

class AboutAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_app)
       binding = ActivityAboutAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Opening Side bar
        binding.bottomAppbarInclude.fabMenuSidebar.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)){
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            } else{
                binding.drawerLayout.openDrawer(GravityCompat.END)
            }
        }

        // Navigation Item Selected Listener
        binding.navView.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.about_app -> {
                    if (true){
                        binding.drawerLayout.closeDrawer(GravityCompat.END)
                        return@setNavigationItemSelectedListener true
                    } else{
                        binding.drawerLayout.closeDrawer(GravityCompat.END)
                        binding.drawerLayout.postDelayed( // Add a slight delay to allow the drawer to close smoothly
                            {
                                var intent = Intent(this, AboutAppActivity::class.java)
                                startActivity(intent)
                            }, 200
                        )
                    }
                }
                R.id.list_pokemon -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.END)
                    binding.drawerLayout.postDelayed( // Add a slight delay to allow the drawer to close smoothly
                        {
                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }, 200
                    )
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
    }
}