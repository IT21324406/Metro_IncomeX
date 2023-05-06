package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val secondActButton = findViewById<Button>(R.id.btnLogin)
        secondActButton.setOnClickListener {
            val Intent5 = Intent(this, LoginPage::class.java)
            startActivity(Intent5)
        }
        val secondActButton2Ch = findViewById<Button>(R.id.button3)
        secondActButton2Ch.setOnClickListener {
            val Intent5Ch = Intent(this, ChangePassword::class.java)
            startActivity(Intent5Ch)
        }
    }
}