package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlin.Boolean as Boolean1

class MainActivity : AppCompatActivity() {
	private lateinit  var navController:NavController


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		navController= findNavController(R.id.fragmentContainerView)
		setupActionBarWithNavController(navController)
	}

	override fun onNavigateUp(): Boolean1 {
		return navController.navigateUp() || super.onNavigateUp()
	}

}