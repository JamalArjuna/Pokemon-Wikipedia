package com.example.pokewiki.ui

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.postDelayed
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pokewiki.R
import com.example.pokewiki.databinding.ActivityMainBinding
import com.example.pokewiki.ui.aboutapp.AboutAppActivity
import com.example.pokewiki.ui.aboutme.AboutMeActivity
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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