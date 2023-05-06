package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MoreDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)

        val logActButton = findViewById<Button>(R.id.btn_go)
        logActButton.setOnClickListener{
            val log = Intent(this,IncomeExpense::class.java)
            startActivity(log)
        }
    }
}