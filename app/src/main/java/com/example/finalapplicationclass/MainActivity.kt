package com.example.finalapplicationclass

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    var navController : NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: Step 1 - Set up project
        // TODO: Step 2 - create nac_graph.xml and connect to nav host
        // TODO: Step 3 - Connect list fragment with blue fragment function
        // TODO: Step 4 - set navArgs for blue fragment
        // TODO: Step 5 -
        // TODO: Step 6 -
        // TODO: Step 7 -

        val toolBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)

        val navHostController: NavHostFragment? = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? NavHostFragment
        navController = navHostController?.navController

        navController?.let {
            NavigationUI.setupActionBarWithNavController(
                activity = this,
                navController = it
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == android.R.id.home -> {
                navController?.popBackStack()
                true
            }
            item.itemId == R.id.main_menu_add -> {
                navController?.navigate(R.id.action_global_addStudentFragment)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}


