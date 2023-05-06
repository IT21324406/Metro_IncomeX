package com.example.additionaltransactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText



class AdditionalIncome : AppCompatActivity() {

    private lateinit var id: AppCompatEditText
    private lateinit var ed_logName: EditText
    private lateinit var spn_logCat: Spinner
    private lateinit var ed_logTrans: EditText
    private lateinit var ed_logAmount: EditText
    private lateinit var spn_logFreq: Spinner
    private lateinit var ed_logDays: EditText
    private lateinit var ed_logDate: EditText
    private lateinit var totalAmount: TextView
    private lateinit var btn_income_add: Button
    private lateinit var btn_income_update: Button
    private lateinit var btn_income_del: Button
    private lateinit var btn_income_view: Button

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additional_income)

        val back2ActButton = findViewById<Button>(R.id.btn_back4)
        back2ActButton.setOnClickListener{
            val back2 = Intent(this,IncomeExpense::class.java)
            startActivity(back2)
        }

        id = findViewById<AppCompatEditText>(R.id.id)
        ed_logName= findViewById<EditText>(R.id.ed_logName)
        spn_logCat= findViewById<Spinner>(R.id.spn_logCat)
        ed_logTrans= findViewById<EditText>(R.id.ed_logTrans)
        ed_logAmount= findViewById<EditText>(R.id.ed_logAmount)
        spn_logFreq= findViewById<Spinner>(R.id.spn_logFreq)
        ed_logDays= findViewById<EditText>(R.id.ed_logDays)
        ed_logDate= findViewById<EditText>(R.id.ed_logDate)
        totalAmount = findViewById<TextView>(R.id.totalAmount)
        btn_income_add= findViewById<Button>(R.id.btn_income_add)
        btn_income_update= findViewById<Button>(R.id.btn_income_update)
        btn_income_del= findViewById<Button>(R.id.btn_income_del)
        btn_income_view= findViewById<Button>(R.id.btn_income_view)

        handleIncomeInserts()
        handleIncomeUpdates()
        handleIncomeViewing()
        handleIncomeDelete()

    }

    fun showToastIncome(text: String) {
        Toast.makeText(this@AdditionalIncome, text, Toast.LENGTH_LONG).show()
    }

    fun showDialogIncome(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    fun clearEditTextsIncome() {
        ed_logName.setText("")
        spn_logCat.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
        ed_logTrans.setText("")
        ed_logAmount.setText("")
        spn_logFreq.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
        ed_logDays.setText("")
        ed_logDate.setText("")
    }

    fun handleIncomeInserts() {
        btn_income_add.setOnClickListener() {
            try {
                val  name= ed_logName.text.toString()
                val  category = spn_logCat.selectedItem.toString()
                val  transactionName=ed_logTrans.text.toString()
                val  amount=ed_logAmount.text.toString()
                val  frequency=spn_logFreq.selectedItem.toString()
                val  days=ed_logDays.text.toString()
                val  date=ed_logDate.text.toString()

                val daysInt = days.toIntOrNull() ?: 0
                val amountDouble = amount.toDoubleOrNull() ?: 0.0
                val price = daysInt * amountDouble

          /*      dbHelper.addIncome(
                    ed_logName.text.toString(),
                    spn_logCat.selectedItem.toString(),
                    ed_logTrans.text.toString(),
                    ed_logAmount.text.toString(),
                    spn_logFreq.selectedItem.toString(),
                    ed_logDays.text.toString(),
                    ed_logDate.text.toString()
                )*/
            val isInsert = dbHelper.addIncome(name, category, transactionName, amount, frequency, days,date)
                totalAmount.text = "$price Rupeese"

                clearEditTextsIncome()

            } catch (e: Exception) {
                e.printStackTrace()
                showToastIncome(e.message.toString())
            }
        }
    }

    fun handleIncomeUpdates() {
        btn_income_update.setOnClickListener {

            try {

                val isUpdate = dbHelper.updateIncome(
                    id.tag.toString(),
                    ed_logName.text.toString(),
                    spn_logCat.selectedItem.toString(),
                    ed_logTrans.text.toString(),
                    ed_logAmount.text.toString(),
                    spn_logFreq.selectedItem.toString(),
                    ed_logDays.text.toString(),
                    ed_logDate.text.toString()
                )
                if (isUpdate == true) {
                    showToastIncome("Data Updated Successfully")
                } else {
                    showToastIncome("Data Not Updated")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToastIncome(e.message.toString())
            }
        }
    }

    fun handleIncomeDelete() {
        btn_income_del.setOnClickListener {
            try {
                dbHelper.deleteIncome(id.tag.toString())
                clearEditTextsIncome()
            }catch (e: Exception) {
                e.printStackTrace()
                showToastIncome(e.message.toString())
            }
        }
    }


    fun handleIncomeViewing() {
        btn_income_view.setOnClickListener {
            val res = dbHelper.allIncome
            if (res.count == 0) {
                showDialogIncome("Error", "No Data Found")
                return@setOnClickListener
            }
            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append("ID: " + res.getString(0) + "\n")
                buffer.append("NAME: " + res.getString(1) + "\n")
                buffer.append("TRANSACTION CATEGORY: " + res.getString(2) + "\n")
                buffer.append("TRANSACTION NAME: " + res.getString(3) + "\n")
                buffer.append("AMOUNT: " + res.getString(4) + "\n")
                buffer.append("FREQUENCY: " + res.getString(5) + "\n")
                buffer.append("NO OF DAYS: " + res.getString(6) + "\n")
                buffer.append("TRANSACTION DATE: " + res.getString(7) + "\n")
                buffer.append("TOTAL AMOUNT: " + totalAmount.text + "\n")
                id.tag = res.getString(0)
            }

            showDialogIncome("Data Listing", buffer.toString())
            }

        }
    }
