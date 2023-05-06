package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class aboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val secondActButton = findViewById<Button>(R.id.btn_back7)
        secondActButton.setOnClickListener{
            //connecting pages
            val Intent6 = Intent(this,MainActivity::class.java)
            startActivity(Intent6)
        }
    }
}