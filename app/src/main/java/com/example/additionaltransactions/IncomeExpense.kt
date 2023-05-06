package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IncomeExpense : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_expense)

        val addIncomeButton = findViewById<Button>(R.id.btn_type1)
        addIncomeButton.setOnClickListener{
            val addIncome = Intent(this,AdditionalIncome::class.java)
            startActivity(addIncome)
        }

        val addExpenseButton = findViewById<Button>(R.id.btn_type2)
        addExpenseButton.setOnClickListener{
            val addExpense = Intent(this,AdditionalExpense::class.java)
            startActivity(addExpense)
        }

        val back1ActButton = findViewById<Button>(R.id.btn_back1)
        back1ActButton.setOnClickListener{
            val back1 = Intent(this,AdditionalTransaction::class.java)
            startActivity(back1)
        }


    }

}