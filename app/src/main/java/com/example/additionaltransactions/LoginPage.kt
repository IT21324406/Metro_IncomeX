package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val secondActButton = findViewById<Button>(R.id.btnLogin)
        secondActButton.setOnClickListener {
            val Intent5 = Intent(this, UserProfile::class.java)
            startActivity(Intent5)
        }

        val secondActButton2 = findViewById<Button>(R.id.button12)
        secondActButton2.setOnClickListener {
            val Intent5 = Intent(this, ForgotPassword::class.java)
            startActivity(Intent5)
        }
        val secondActButton3 = findViewById<Button>(R.id.button13)
        secondActButton3.setOnClickListener {
            val Intent5 = Intent(this,registration::class.java)
            startActivity(Intent5)
        }
    }
}