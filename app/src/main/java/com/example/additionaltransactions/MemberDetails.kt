package com.example.additionaltransactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText

class MemberDetails : AppCompatActivity() {


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
    private lateinit var btnFamDetails: Button
    private lateinit var famView: Button
    private lateinit var famUpdate: Button
    private lateinit var famDelete: Button

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_details)

        famView = findViewById<Button>(R.id.famView)
        famUpdate = findViewById<Button>(R.id.famUpdate)
        famDelete = findViewById<Button>(R.id.famDelete)

        handleUpdatesMem()
        handleViewingMem()
        handleDeleteMem()
    }

    fun showToastMem(text: String) {
        Toast.makeText(this@MemberDetails, text, Toast.LENGTH_LONG).show()
    }
    fun showDialogMem(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
    fun clearEditTextsMem() {
        editTextNumberDecimal3.setText("")
        editTextNumberDecimal.setText("")
        editTextNumberDecimal5.setText("")
        editTextTextPersonName13.setText("")
        editTextTextPersonName14.setText("")
        editTextTextPersonName15.setText("")
        editTextTextPersonName16.setText("")
        cbTransport.isChecked = false
        cbIncome.isChecked = false
    }
    fun handleUpdatesMem() {
        famUpdate.setOnClickListener {

            try {

                val isUpdate = dbHelper.updateFamily(
                    id.tag.toString(),
                    editTextNumberDecimal3.text.toString(),
                    editTextNumberDecimal.text.toString(),
                    editTextNumberDecimal5.text.toString(),
                    editTextTextPersonName13.text.toString(),
                    editTextTextPersonName14.text.toString(),
                    editTextTextPersonName15.text.toString(),
                    editTextTextPersonName16.text.toString(),
                    cbTransport.isChecked.toString(),
                    cbIncome.isChecked.toString(),
                )
                if (isUpdate == true) {
                    showToastMem("Data Updated Successfully")
                } else {
                    showToastMem("Data Not Updated")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToastMem(e.message.toString())
            }
        }
    }
    fun handleViewingMem() {
        famView.setOnClickListener {
            val res = dbHelper.allFamily
            if (res.count == 0) {
                showDialogMem("Error", "No Data Found")
                return@setOnClickListener
            }
            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append("ID: " + res.getString(0) + "\n")
                buffer.append("CHILDREN: " + res.getString(1) + "\n")
                buffer.append("ADULT: " + res.getString(2) + "\n")
                buffer.append("NUMBER: " + res.getString(3) + "\n")
                buffer.append("NAME: " + res.getString(4) + "\n")
                buffer.append("AGE: " + res.getString(5) + "\n")
                buffer.append("OCCUPATION: " + res.getString(6) + "\n")
                buffer.append("SCHOOL/ COMPANY NAME: " + res.getString(7) + "\n")
                buffer.append("TRANSPORT: " + res.getString(8) + "\n")
                buffer.append("INCOME: " + res.getString(9) + "\n")
                id.tag = res.getString(0)
            }
            showDialogMem("Data Listing", buffer.toString())
        }
    }
    fun handleDeleteMem() {
        famDelete.setOnClickListener {
            try {
                dbHelper.deleteFamily(id.tag.toString())
                clearEditTextsMem()
            }catch (e: Exception) {
                e.printStackTrace()
                showToastMem(e.message.toString())
            }
        }
    }
}