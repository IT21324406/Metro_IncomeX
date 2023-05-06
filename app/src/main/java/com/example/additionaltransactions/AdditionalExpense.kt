package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText

class AdditionalExpense : AppCompatActivity() {

    private lateinit var id1: AppCompatEditText //layout id
    private lateinit var ed_logName1: EditText
    private lateinit var spn_logCat1: Spinner
    private lateinit var ed_logTrans1: EditText
    private lateinit var ed_logAmount1: EditText
    private lateinit var spn_logFreq1: Spinner
    private lateinit var ed_logDays1: EditText
    private lateinit var ed_logDate1: EditText
    private lateinit var totalAmountExpense: TextView
    private lateinit var btn_expense_add: Button
    private lateinit var btn_expense_update: Button
    private lateinit var btn_expense_del: Button
    private lateinit var btn_expense_view: Button

    internal var dbHelper1 = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additional_expense)

        val back3ActButton = findViewById<Button>(R.id.btn_back5)
        back3ActButton.setOnClickListener {
            val back3 = Intent(this, IncomeExpense::class.java)
            startActivity(back3)
        }

        id1 = findViewById<AppCompatEditText>(R.id.id1)
        ed_logName1= findViewById<EditText>(R.id.ed_logName1)
        spn_logCat1= findViewById<Spinner>(R.id.spn_logCat1)
        ed_logTrans1= findViewById<EditText>(R.id.ed_logTrans1)
        ed_logAmount1= findViewById<EditText>(R.id.ed_logAmount1)
        spn_logFreq1= findViewById<Spinner>(R.id.spn_logFreq1)
        ed_logDays1= findViewById<EditText>(R.id.ed_logDays1)
        ed_logDate1= findViewById<EditText>(R.id.ed_logDate1)
        totalAmountExpense = findViewById<TextView>(R.id.totalAmountExpense)
        btn_expense_add= findViewById<Button>(R.id.btn_expense_add)
        btn_expense_update= findViewById<Button>(R.id.btn_expense_update)
        btn_expense_del= findViewById<Button>(R.id.btn_expense_del)
        btn_expense_view= findViewById<Button>(R.id.btn_expense_view)

        handleInsertsExpense()
        handleUpdatesExpense()
        handleViewingExpense()
        handleDeleteExpense()
    }
    fun showToastEx(text: String) {
        Toast.makeText(this@AdditionalExpense, text, Toast.LENGTH_LONG).show()
    }

    fun showDialogEx(title: String, Message: String) {
        val builderEx = AlertDialog.Builder(this)
        builderEx.setCancelable(true)
        builderEx.setTitle(title)
        builderEx.setMessage(Message)
        builderEx.show()
    }

    fun clearEditTextsEx() {
        ed_logName1.setText("")
        spn_logCat1.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
        ed_logTrans1.setText("")
        ed_logAmount1.setText("")
        spn_logFreq1.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
        ed_logDays1.setText("")
        ed_logDate1.setText("")
    }


    fun handleInsertsExpense() {
        btn_expense_add.setOnClickListener() {
            try {
                val  exName= ed_logName1.text.toString()
                val  exCategory = spn_logCat1.selectedItem.toString()
                val  exTransactionName=ed_logTrans1.text.toString()
                val exAmount=ed_logAmount1.text.toString()
                val  exFrequency=spn_logFreq1.selectedItem.toString()
                val  exDays=ed_logDays1.text.toString()
                val  exDate=ed_logDate1.text.toString()

                val exDaysInt = exDays.toIntOrNull() ?: 0
                val exAmountDouble = exAmount.toDoubleOrNull() ?: 0.0
                val priceEx = exDaysInt * exAmountDouble

                /*      dbHelper.addIncome(
                          ed_logName.text.toString(),
                          spn_logCat.selectedItem.toString(),
                          ed_logTrans.text.toString(),
                          ed_logAmount.text.toString(),
                          spn_logFreq.selectedItem.toString(),
                          ed_logDays.text.toString(),
                          ed_logDate.text.toString()
                      )*/
                 dbHelper1.addExpense(exName, exCategory, exTransactionName, exAmount, exFrequency, exDays, exDate)
                totalAmountExpense.text = "$priceEx Rupeese"

                clearEditTextsEx()

            } catch (e: Exception) {
                e.printStackTrace()
                showToastEx(e.message.toString())
            }
        }
    }
    fun handleUpdatesExpense() {
        btn_expense_update.setOnClickListener {

            try {

                val isUpdateEx = dbHelper1.updateExpense(
                    id1.tag.toString(),
                    ed_logName1.text.toString(),
                    spn_logCat1.selectedItem.toString(),
                    ed_logTrans1.text.toString(),
                    ed_logAmount1.text.toString(),
                    spn_logFreq1.selectedItem.toString(),
                    ed_logDays1.text.toString(),
                    ed_logDate1.text.toString()
                )
                if (isUpdateEx == true) {
                    showToastEx("Data Updated Successfully")
                } else {
                    showToastEx("Data Not Updated")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToastEx(e.message.toString())
            }
        }
    }

    fun  handleDeleteExpense() {
        btn_expense_del.setOnClickListener {
            try {
                dbHelper1.deleteExpense(id1.tag.toString())
                clearEditTextsEx()
            }catch (e: Exception) {
                e.printStackTrace()
                showToastEx(e.message.toString())
            }
        }
    }

    fun handleViewingExpense() {
        btn_expense_view.setOnClickListener {
            val rs = dbHelper1.allExpense
            if (rs.count == 0) {
                showDialogEx("Error", "No Data Found")
                return@setOnClickListener
            }
            val bufferEx = StringBuffer()
            while (rs.moveToNext()) {
                bufferEx.append("ID: " + rs.getString(0) + "\n")
                bufferEx.append("NAME: " + rs.getString(1) + "\n")
                bufferEx.append("TRANSACTION CATEGORY: " + rs.getString(2) + "\n")
                bufferEx.append("TRANSACTION NAME: " + rs.getString(3) + "\n")
                bufferEx.append("AMOUNT: " + rs.getString(4) + "\n")
                bufferEx.append("FREQUENCY: " + rs.getString(5) + "\n")
                bufferEx.append("NO OF DAYS: " + rs.getString(6) + "\n")
                bufferEx.append("TRANSACTION DATE: " + rs.getString(7) + "\n")
                bufferEx.append("TOTAL AMOUNT: " + totalAmountExpense.text + "\n")
                id1.tag = rs.getString(0)
            }

            showDialogEx("Data Listing", bufferEx.toString())
        }

    }
}