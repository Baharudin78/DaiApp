package com.baharudin.daiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.baharudin.daiapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navigationFragment.findNavController()
        binding.apply {
             bottomNav.setupWithNavController(navController)
        }
    }

    override fun navigateUpTo(upIntent: Intent?): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }
}