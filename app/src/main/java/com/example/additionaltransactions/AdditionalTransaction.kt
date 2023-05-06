package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdditionalTransaction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additional_transactions)

        val moreActButton = findViewById<Button>(R.id.btn_page)
        moreActButton.setOnClickListener{
            val more = Intent(this,MoreDetails::class.java)
            startActivity(more)
        }

        val homeActButton = findViewById<Button>(R.id.btnBack_6)
        homeActButton.setOnClickListener{
            val homeMore = Intent(this,HomePage::class.java)
            startActivity(homeMore)
        }
    }
}