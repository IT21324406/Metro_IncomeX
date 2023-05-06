package com.example.additionaltransactions

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

class UserDetails : AppCompatActivity() {


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
    private lateinit var userUpdate: Button
    private lateinit var userView: Button

    internal var dbHelper = DatabaseHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        userView = findViewById<Button>(R.id.userView)
        userUpdate = findViewById<Button>(R.id.userUpdate)


        updateRegistrations()
        handleViewingUse()
    }
    fun showToastUse(text: String) {
        Toast.makeText(this@UserDetails, text, Toast.LENGTH_LONG).show()
    }
    fun showDialogUse(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
    fun clearEditTextsUse() {
        edtName.setText("")
        edtPhone.setText("")
        edtAddress.setText("")
        edtOccupation.setText("")
        cbMy.isChecked = false
        cbFam.isChecked = false
        edtUsername.setText("")
        edtPassword.setText("")
        spQuestion.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
        edtAnswer.setText("")
    }
    fun updateRegistrations() {
        userUpdate.setOnClickListener {

            try {

                val isUpdate = dbHelper.updateRegistrations(
                    id.tag.toString(),
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
                if (isUpdate == true) {
                    showToastUse("Data Updated Successfully")
                } else {
                    showToastUse("Data Not Updated")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToastUse(e.message.toString())
            }

        }
    }
    fun handleViewingUse() {
        userView.setOnClickListener {
            val res = dbHelper.allRegistrations
            if (res.count == 0) {
                showDialogUse("Error", "No Data Found")
                return@setOnClickListener
            }
            val buffer = StringBuffer()

            while (res.moveToNext()) {
                buffer.append("ID: " + res.getString(0) + "\n")
                buffer.append("NAME: " + res.getString(1) + "\n")
                buffer.append("AGE: " + res.getString(2) + "\n")
                buffer.append("ADDRESS: " + res.getString(3) + "\n")
                buffer.append("OCCUPATION: " + res.getString(4) + "\n")
                buffer.append("MYSELF: " + res.getString(5) + "\n")
                buffer.append("FAMILY: " + res.getString(6) + "\n")
                buffer.append("USERNAME: " + res.getString(7) + "\n")
                buffer.append("PASSWORD: " + res.getString(8) + "\n")
                buffer.append("QUESTION: " + res.getString(9) + "\n")
                buffer.append("ANSWER: " + res.getString(10) + "\n")
                id.tag = res.getString(0)
            }
            showDialogUse("Data Listing", buffer.toString())
        }
    }

}