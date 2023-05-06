package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addDashButton = findViewById<Button>(R.id.btnHm)
        addDashButton.setOnClickListener{
            val dash = Intent(this,HomePage::class.java)
            startActivity(dash)
        }

        val setDashButton = findViewById<Button>(R.id.btnSet)
        setDashButton.setOnClickListener{
            val setDash = Intent(this,Settings::class.java)
            startActivity(setDash)
        }
        val aboutDashButton = findViewById<Button>(R.id.btnSearch)
        aboutDashButton.setOnClickListener{
            val aboutDash = Intent(this,aboutUs::class.java)
            startActivity(aboutDash)
        }

    }
}