package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class savings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savings)

        val data = intent.getStringArrayListExtra("data")

        // Display the data on the screen
        val textView5 = findViewById<TextView>(R.id.textView5)
        textView5.text = data?.joinToString(separator = "\n\n")

        val secondActButton = findViewById<Button>(R.id.btn_back9)
        secondActButton.setOnClickListener{
            //connecting pages
            val Intent9 = Intent(this,MainActivity::class.java)
            startActivity(Intent9)
        }
    }
}