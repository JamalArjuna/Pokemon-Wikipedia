package com.example.pokewiki.ui.aboutapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

    }
}