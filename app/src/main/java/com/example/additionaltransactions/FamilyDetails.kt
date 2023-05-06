package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import com.example.additionaltransactions.DatabaseHelper

class FamilyDetails : AppCompatActivity() {

    private lateinit var id: AppCompatEditText
    private lateinit var editTextNumberDecimal3: EditText
    private lateinit var editTextNumberDecimal: EditText
    private lateinit var editTextNumberDecimal5: EditText
    private lateinit var editTextTextPersonName13: EditText
    private lateinit var editTextTextPersonName14: EditText
    private lateinit var editTextTextPersonName15: EditText
    private lateinit var editTextTextPersonName16: EditText
    private lateinit var cbTransport: CheckBox
    private lateinit var cbIncome: CheckBox
    private lateinit var btnSubmit: Button

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_details)

        val secondActButton = findViewById<Button>(R.id.btnBack)
        secondActButton.setOnClickListener {
            val Intent5 = Intent(this,UserProfile::class.java)
            startActivity(Intent5)
        }

        val secondActButton2 = findViewById<Button>(R.id.btnSubmit )
        secondActButton2.setOnClickListener {
            val Intent5 = Intent(this, UserProfile::class.java)
            startActivity(Intent5)
        }

        //id = findViewById<AppCompatEditText>(R.id.id)
        editTextNumberDecimal3 = findViewById<EditText>(R.id.editTextNumberDecimal3)
        editTextNumberDecimal = findViewById<EditText>(R.id.editTextNumberDecimal)
        editTextNumberDecimal5 = findViewById<EditText>(R.id.editTextNumberDecimal5)
        editTextTextPersonName13 = findViewById<EditText>(R.id.editTextTextPersonName13)
        editTextTextPersonName14 = findViewById<EditText>(R.id.editTextTextPersonName14)
        editTextTextPersonName15 = findViewById<EditText>(R.id.editTextTextPersonName15)
        editTextTextPersonName16 = findViewById<EditText>(R.id.editTextTextPersonName16)
        cbTransport = findViewById<CheckBox>(R.id.cbTransport)
        cbIncome = findViewById<CheckBox>(R.id.cbIncome)

        handleInsertsFam()
    }

    fun showToastFam(text: String) {
        Toast.makeText(this@FamilyDetails, text, Toast.LENGTH_LONG).show()

    }

    fun showDialogFam(title:String, Message:String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()

    }

    fun clearEditTextsFam() {
        editTextNumberDecimal3.setText("")
        editTextNumberDecimal.setText("")
        editTextNumberDecimal5.setText("")
        editTextTextPersonName13.setText("")
        editTextTextPersonName14 .setText("")
        editTextTextPersonName15.setText("")
        editTextTextPersonName16.setText("")
        cbTransport.isChecked = false
        cbIncome.isChecked = false

    }

    fun handleInsertsFam() {
        btnSubmit.setOnClickListener() {
            try {
                dbHelper.addFamily(
                    editTextNumberDecimal3.text.toString(),
                    editTextNumberDecimal.text.toString(),
                    editTextNumberDecimal5.text.toString(),
                    editTextTextPersonName13.text.toString(),
                    editTextTextPersonName14.text.toString(),
                    editTextTextPersonName15.text.toString(),
                    editTextTextPersonName16.text.toString(),
                    cbTransport.isChecked.toString(),
                    cbIncome.isChecked.toString()
                )
                clearEditTextsFam()
            }catch (e: Exception) {
                e.printStackTrace()
                showToastFam(e.message.toString())
            }
        }

    }
}