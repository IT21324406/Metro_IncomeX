package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val loggerButton = findViewById<Button>(R.id.btnLogger)
        loggerButton.setOnClickListener{
            val logger = Intent(this,AdditionalTransaction::class.java)
            startActivity(logger)
        }

        val dashboardButton = findViewById<Button>(R.id.btnSettings)
        dashboardButton.setOnClickListener{
            val dashboard = Intent(this,MainActivity::class.java)
            startActivity(dashboard)
        }

        val notifyButton = findViewById<Button>(R.id.btnAbout)
        notifyButton.setOnClickListener{
            val notify = Intent(this,notifications::class.java)
            startActivity(notify)
        }

        val finButton = findViewById<Button>(R.id.btnFinan)
        finButton.setOnClickListener{
            val fin = Intent(this,startPage::class.java)
            startActivity(fin)
        }
    }
}