package com.example.wastewise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wastewise.databinding.ActivityBottomNavigationViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationView : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation_view)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_camera, R.id.navigation_booklet, R.id.navigation_profile
//            )
//        )
//
//        // Check if ActionBar is not null before calling setupActionBarWithNavController
//        supportActionBar?.let {
//            setupActionBarWithNavController(navController, appBarConfiguration)
//        }

        navView.setupWithNavController(navController)
    }
}
