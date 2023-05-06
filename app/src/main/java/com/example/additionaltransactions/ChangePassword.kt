package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val secondActButtonWx = findViewById<Button>(R.id.button8)
        secondActButtonWx.setOnClickListener {
            val Intent5Wx = Intent(this, ForgotPassword::class.java)
            startActivity(Intent5Wx)
        }

        val secondActButton2 = findViewById<Button>(R.id.button9)
        secondActButton2.setOnClickListener {
            val Intent5Wf = Intent(this, LoginPage::class.java)
            startActivity(Intent5Wf)
        }

    }
}