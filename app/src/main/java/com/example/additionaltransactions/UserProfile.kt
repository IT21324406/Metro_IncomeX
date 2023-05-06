package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        val secondActButton = findViewById<Button>(R.id.addFamDetails)
        secondActButton.setOnClickListener {
            val Intent5 = Intent(this, MemberDetails::class.java)
            startActivity(Intent5)
        }

        val secondActButton2 = findViewById<Button>(R.id.btnViewFam)
        secondActButton2.setOnClickListener {
            val Intent5 = Intent(this,registration::class.java)
            startActivity(Intent5)
        }

        val secondActButton3 = findViewById<Button>(R.id.btnViewProfile)
        secondActButton3.setOnClickListener {
            val Intent5 = Intent(this, UserDetails::class.java)
            startActivity(Intent5)
        }

        val secondActButton4 = findViewById<Button>(R.id.btnLogout)
        secondActButton4.setOnClickListener {
            val Intent5 = Intent(this,LoginPage::class.java)
            startActivity(Intent5)
        }
    }

}