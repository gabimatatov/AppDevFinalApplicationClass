package com.example.finalapplicationclass

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

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

    }
}


