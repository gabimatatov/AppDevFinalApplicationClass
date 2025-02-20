package com.example.finalapplicationclass

import android.graphics.Color
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
import com.google.android.material.bottomnavigation.BottomNavigationView

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


        val toolBar: Toolbar = findViewById(R.id.toolbar)
        toolBar.setBackgroundColor(Color.parseColor("#333333"))
        setSupportActionBar(toolBar)

        val navHostController: NavHostFragment? = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? NavHostFragment
        navController = navHostController?.navController

        navController?.let {
            NavigationUI.setupActionBarWithNavController(
                activity = this,
                navController = it
            )
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_bar)
        navController?.let { NavigationUI.setupWithNavController(bottomNavigationView, it) }

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
            else -> {
                navController?.let {
                    NavigationUI.onNavDestinationSelected(item, it)
                }
                true
            }
        }
    }
}


