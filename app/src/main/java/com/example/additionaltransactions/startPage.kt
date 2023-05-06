package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class startPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_page)

        val startButton = findViewById<Button>(R.id.start)
        startButton.setOnClickListener{
            val finance = Intent(this,financialform::class.java)
            startActivity(finance)
        }
    }
}