package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class notifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val notifyBackButton = findViewById<Button>(R.id.btn_back8)
        notifyBackButton.setOnClickListener{
            val notifyBack = Intent(this,HomePage::class.java)
            startActivity(notifyBack)
        }
    }
}