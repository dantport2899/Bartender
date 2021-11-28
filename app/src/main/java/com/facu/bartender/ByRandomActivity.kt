package com.facu.bartender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class ByRandomActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_by_random)

        navController=findNavController(R.id.nav_host_fragment_ByRandom)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}