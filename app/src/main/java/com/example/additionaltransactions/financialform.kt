package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class financialform : AppCompatActivity() {

    private lateinit var id: LinearLayout
    private lateinit var salary: EditText
    private lateinit var otherInc: EditText
    private lateinit var rent: EditText
    private lateinit var grocery: EditText
    private lateinit var waterBill: EditText
    private lateinit var electBill: EditText
    private lateinit var eduExpense: EditText
    private lateinit var insert: Button
    private lateinit var view: Button
    private lateinit var update: Button
    private lateinit var delete: Button

    internal var dbHelper = DatabaseHelper (this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financialform)

        val viewSaveButton = findViewById<Button>(R.id.savingsBtn)
        viewSaveButton.setOnClickListener{
            val viewS = Intent(this,savings::class.java)
            startActivity(viewS)
        }

        id = findViewById<LinearLayout>(R.id.id)
        salary = findViewById<EditText>(R.id.salary)
        otherInc = findViewById<EditText>(R.id.otherInc)
        rent = findViewById<EditText>(R.id.rent)
        grocery = findViewById<EditText>(R.id.grocery)
        waterBill = findViewById<EditText>(R.id.waterBill)
        electBill = findViewById<EditText>(R.id.electBill)
        eduExpense = findViewById<EditText>(R.id.eduExpense)
        insert = findViewById<Button>(R.id.insertBtn)
        view = findViewById<Button>(R.id.viewBtn)
        update = findViewById<Button>(R.id.updateBtn)
        delete = findViewById<Button>(R.id.deleteBtn)


        handleInserts()
        handleUpdates()
        handleDeletes()
        handleViewing()
    }
    fun showToast (text: String) {
        Toast.makeText(this@financialform, text, Toast.LENGTH_LONG).show()
    }

    fun showDialog (title: String, Message: String) {
        val builder =  AlertDialog. Builder (this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage (Message)
        builder.show()
    }

    fun clearEditTexts() {
        salary.setText("")
        otherInc.setText("")
        rent.setText("")
        grocery.setText("")
        waterBill.setText("")
        electBill.setText("")
        eduExpense.setText("")
        //idTxt.setText("")
    }


    fun handleInserts() {
        insert.setOnClickListener {
            try {
                dbHelper.insertData(
                    salary.text.toString(),
                    otherInc.text.toString(),
                    rent.text.toString(),
                    grocery.text.toString(),
                    waterBill.text.toString(),
                    electBill.text.toString(),
                    eduExpense.text.toString()
                )
                incomeadd()
                clearEditTexts()
            } catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }

    }

    private fun incomeadd() {
        try {
            val sal = salary.text.toString().toDoubleOrNull() ?: 0.00
            val otherinc = otherInc.text.toString().toDoubleOrNull() ?: 0.00
            val rnt = rent.text.toString().toDoubleOrNull() ?: 0.00
            val groc = grocery.text.toString().toDoubleOrNull() ?: 0.00
            val wbill = waterBill.text.toString().toDoubleOrNull() ?: 0.00
            val ebill = electBill.text.toString().toDoubleOrNull() ?: 0.00
            val eduexp = eduExpense.text.toString().toDoubleOrNull() ?: 0.00

            val incomeDouble = (sal + otherinc)
            val expenseDouble = (rnt + groc + wbill + ebill + eduexp)
            val savings = expenseDouble - incomeDouble


            val data = ArrayList<String>()
            data.add(incomeDouble.toString())
            data.add(expenseDouble.toString())
            data.add(savings.toString())

            // Pass the data to the next activity using an Intent
            val intent = Intent(this, savings::class.java)
            intent.putStringArrayListExtra("data", data)
            startActivity(intent)

            //totalAmount.text = "$savings Rupeese"

            clearEditTexts()


        } catch (e: Exception) {
            e.printStackTrace()
            showToast(e.message.toString())
        }

    }


    fun handleUpdates(){
        update.setOnClickListener {
            try {
                val isUpdate = dbHelper.updateData(
                    id.tag.toString(),
                    salary.text.toString(),
                    otherInc.text.toString(),
                    rent.text.toString(),
                    grocery.text.toString(),
                    waterBill.text.toString(),
                    electBill.text.toString(),
                    eduExpense.text.toString()
                )
                if (isUpdate) {
                    showToast("Data Updated Successfully")
                } else {
                    showToast("Data Not Updated")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }

    }

    /*fun handleDeletes() {
        delete.setOnClickListener {
            val res = dbHelper.allData
            if (res.count == 0) {
                showDialog("Error ", "No Data Found")
                return@setOnClickListener
            }

            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append("ID :" + res.getString(0) + "\n")
                buffer.append("Salary :" + res.getString(1) + "\n")
                buffer.append("Other Income :" + res.getString(2) + "\n")
                buffer.append("Rent :" + res.getString(3) + "\n")
                //buffer.append("From Date :" + res.getString(4) + "\n")
                //buffer.append("Number of Days :" + res.getString(5) + "\n")
            }
            showDialog("Data Listing", buffer.toString())


        }
    }*/

    fun handleDeletes() {
        delete.setOnClickListener {
            try {
                dbHelper.deleteData(id.tag.toString())
                clearEditTexts()
            }catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun handleViewing() {
        view.setOnClickListener {
            val res = dbHelper.allData
            if (res.count == 0) {
                showDialog("Error", "No Data Found")
                return@setOnClickListener
            }
            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append("ID :" + res.getString(0) + "\n")
                buffer.append("Salary :" + res.getString(1) + "\n")
                buffer.append("Other Income :" + res.getString(2) + "\n")
                buffer.append("Rent :" + res.getString(3) + "\n")
                buffer.append("Grocery: " + res.getString(4) + "\n")
                buffer.append("Water Bill: " + res.getString(5) + "\n")
                buffer.append("Elect Bill: " + res.getString(5) + "\n")
                buffer.append("Edu Expense: " + res.getString(5) + "\n")
                id.tag = res.getString(0)
            }
            showDialog("Data Listing", buffer.toString())
        }
    }

}