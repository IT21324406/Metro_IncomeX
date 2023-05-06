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

class registration : AppCompatActivity() {

    private lateinit var id: AppCompatEditText
    private lateinit var edtName: EditText
    private lateinit var edtPhone: EditText
    private lateinit var edtAddress: EditText
    private lateinit var edtOccupation: EditText
    private lateinit var cbMy: CheckBox
    private lateinit var cbFam: CheckBox
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var spQuestion: Spinner
    private lateinit var edtAnswer: EditText
    private lateinit var btnSave: Button

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val secondActButton = findViewById<Button>(R.id.btnSave)
        secondActButton.setOnClickListener {
            val Intent5 = Intent(this, UserProfile::class.java)
            startActivity(Intent5)
        }
        val secondActButton2 = findViewById<Button>(R.id.btnBack)
        secondActButton2.setOnClickListener {
            val Intent5 = Intent(this, LoginPage::class.java)
            startActivity(Intent5)
        }

        //id = findViewById<AppCompatEditText>(R.id.id)
        edtName = findViewById<EditText>(R.id.edtName)
        edtPhone = findViewById<EditText>(R.id.edtPhone)
        edtAddress = findViewById<EditText>(R.id.edtAddress)
        edtOccupation = findViewById<EditText>(R.id.edtOccupation)
        cbMy = findViewById<CheckBox>(R.id.cbTransport)
        cbFam = findViewById<CheckBox>(R.id.cbFam)
        edtUsername = findViewById<EditText>(R.id.edtUsername)
        edtPassword = findViewById<EditText>(R.id.edtPassword)
        spQuestion = findViewById<Spinner>(R.id.spQuestion)
        edtAnswer = findViewById<EditText>(R.id.edtAnswer)
        btnSave = findViewById<Button>(R.id.btnSave)

        handleInsertsReg()
    }
    fun showToastReg(text: String) {
        Toast.makeText(this@registration, text, Toast.LENGTH_LONG).show()

    }

    fun showDialogReg(title:String, Message:String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()

    }

    fun clearEditTextsReg() {
        edtName.setText("")
        edtPhone.setText("")
        edtAddress.setText("")
        edtOccupation.setText("")
        cbMy.isChecked = false
        cbFam.isChecked = false
        edtUsername.setText("")
        edtPassword.setText("")
        spQuestion.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
        edtAnswer.setText("")

    }

    fun handleInsertsReg() {
        btnSave.setOnClickListener() {
            try {
                dbHelper.addRegistrations(
                    edtName.text.toString(),
                    edtPhone.text.toString(),
                    edtAddress.text.toString(),
                    edtOccupation.text.toString(),
                    cbMy.isChecked.toString(),
                    cbFam.isChecked.toString(),
                    edtUsername.text.toString(),
                    edtPassword.text.toString(),
                    spQuestion.selectedItem.toString(),
                    edtAnswer.text.toString()
                )
                clearEditTextsReg()
            }catch (e: Exception) {
                e.printStackTrace()
                showToastReg(e.message.toString())
            }
        }

    }
}