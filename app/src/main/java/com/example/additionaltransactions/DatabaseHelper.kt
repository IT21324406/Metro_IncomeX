package com.example.additionaltransactions

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        //INCOME FORM
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "incomex.db"
        private const val TABLE_NAME = "additionallog_table"
        private const val COL_ID = "id"
        private const val COL_MEMBER_NAME = "member_name"
        private const val COL_TRANSACTION_CATEGORY = "transaction_category"
        private const val COL_TRANSACTION_NAME = "transaction_name"
        private const val COL_TRANSACTION_AMOUNT = "transaction_amount"
        private const val COL_TRANSACTION_FREQUENCY = "transaction_frequency"
        private const val COL_NO_OF_DAYS = "no_of_days"
        private const val COL_TRANSACTION_DATE = "transaction_date"

        //EXPENSE FORM
        private const val TABLE_NAME_EXPENSE = "AdditionalExpense"
        private const val ID = "id_expense"
        private const val MEMBER_NAME = "member_name_expense"
        private const val TRANSACTION_CATEGORY = "transaction_category_expense"
        private const val TRANSACTION_NAME = "transaction_name_expense"
        private const val TRANSACTION_AMOUNT = "transaction_amount_expense"
        private const val TRANSACTION_FREQUENCY = "transaction_frequency_expense"
        private const val NO_OF_DAYS = "no_of_days_expense"
        private const val TRANSACTION_DATE = "transaction_date_expense"


        //AMANDI FORM

        private const val TABLE_NAME_FINANCIAL = "IncomeX_table"
        private const val COL_1= "ID"
        private const val COL_2= "SALARY"
        private const val COL_3 = "OTHERINCOME"
        private const val COL_4 = "RENT"
        private const val COL_5 = "GROCERY"
        private const val COL_6 = "WATERBILL"
        private const val COL_7 = "ELECTBILL"
        private const val COL_8 = "EDUEXPENSE"



        //AMANDA REGISTRATION
        private const val TABLE_NAME_REGISTRATION = "registrations_table"
        private const val COL_ID1 = "id"
        private const val COL_CUSTOMER_NAME = "customer_name"
        private const val COL_CUSTOMER_PHONE = "phone"
        private const val COL_CUSTOMER_ADDRESS = "address"
        private const val COL_CUSTOMER_OCCUPATION = "occupation"
        private const val COL_MYSELF = "myself"
        private const val COL_FAMILY = "family"
        private const val COL_USERNAME = "username"
        private const val COL_PASSWORD = "password"
        private const val COL_SELECTED_QUESTION = "question"
        private const val COL_ANSWER = "answer"


        //AMANDA FAMILY
        private const val TABLE_NAME_FAM = "family_table"
        private const val COL_ID2 = "id"
        private const val COL_CHILDREN_NUMBER = "children_num"
        private const val COL_ADULTS_NUMBER = "adults_num"
        private const val COL_STUDENT_NUMBER = "student_num"
        private const val COL_MEMBER_NAME1 = "member_name"
        private const val COL_MEMBER_AGE = "member_age"
        private const val COL_MEMBER_OCCUPATION = "member_occupation"
        private const val COL_MEMBER_SCLCOM = "member_sclcom"
        private const val COL_TRANSPORT = "transport"
        private const val COL_INCOME = "income"
    }






    override fun onCreate(db: SQLiteDatabase?) {
        //INCOME FORM

        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_MEMBER_NAME TEXT," +
                "$COL_TRANSACTION_CATEGORY TEXT," +
                "$COL_TRANSACTION_NAME TEXT," +
                "$COL_TRANSACTION_AMOUNT TEXT," +
                "$COL_TRANSACTION_FREQUENCY TEXT," +
                "$COL_NO_OF_DAYS TEXT," +
                "$COL_TRANSACTION_DATE TEXT" + ")"
        db?.execSQL(createTable)

        //EXPENSE FORM

        val createTableExpense = "CREATE TABLE $TABLE_NAME_EXPENSE ($ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$MEMBER_NAME TEXT," +
                "$TRANSACTION_CATEGORY TEXT," +
                "$TRANSACTION_NAME TEXT," +
                "$TRANSACTION_AMOUNT TEXT," +
                "$TRANSACTION_FREQUENCY TEXT," +
                "$NO_OF_DAYS TEXT," +
                "$TRANSACTION_DATE TEXT" + ")"
        db?.execSQL(createTableExpense)


        //AMANDI FORM

        val createTableFinance =
            "CREATE TABLE $TABLE_NAME_FINANCIAL($COL_1 INTEGER PRIMARY KEY AUTOINCREMENT,$COL_2 TEXT,$COL_3 TEXT,$COL_4 TEXT,$COL_5 TEXT,$COL_6 TEXT, $COL_7 TEXT,$COL_8 TEXT)"
        db?.execSQL(createTableFinance)


        //AMANDA REGISTRATION
        val createTableRegistration = "CREATE TABLE $TABLE_NAME_REGISTRATION ($COL_ID1 INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_CUSTOMER_NAME TEXT," +
                "$COL_CUSTOMER_PHONE TEXT," +
                "$COL_CUSTOMER_ADDRESS TEXT," +
                "$COL_CUSTOMER_OCCUPATION TEXT," +
                "$COL_MYSELF TEXT," +
                "$COL_FAMILY TEXT," +
                "$COL_USERNAME TEXT," +
                "$COL_PASSWORD TEXT," +
                "$COL_SELECTED_QUESTION TEXT," +
                "$COL_ANSWER TEXT" +")"
        db?.execSQL(createTableRegistration)

        //AMANDA FAMILY
        val createTableFam = "CREATE TABLE $TABLE_NAME_FAM ($COL_ID2 INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_CHILDREN_NUMBER TEXT," +
                "$COL_ADULTS_NUMBER TEXT," +
                "$COL_STUDENT_NUMBER TEXT," +
                "$COL_MEMBER_NAME1 TEXT," +
                "$COL_MEMBER_AGE TEXT," +
                "$COL_MEMBER_OCCUPATION TEXT," +
                "$COL_MEMBER_SCLCOM TEXT," +
                "$COL_TRANSPORT TEXT," +
                "$COL_INCOME TEXT" +")"
        db?.execSQL(createTableFam)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_EXPENSE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_FINANCIAL")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_REGISTRATION")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_FAM")
        onCreate(db)

    }

                //CRUD - IT21324406 - INCOME_FORM
    //view details
    val allIncome : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
            return res
        }


    //insert
    fun addIncome(name: String, category: String, transaction: String, amount: String, frequency: String, days: String, date: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_MEMBER_NAME,name)
        values.put(COL_TRANSACTION_CATEGORY,category)
        values.put(COL_TRANSACTION_NAME,transaction)
        values.put(COL_TRANSACTION_AMOUNT,amount)
        values.put(COL_TRANSACTION_FREQUENCY,frequency)
        values.put(COL_NO_OF_DAYS,days)
        values.put(COL_TRANSACTION_DATE,date)

        db.insert(TABLE_NAME,null,values)


    }

    //delete
    fun deleteIncome(_id: String):Boolean{
        val db = this.writableDatabase
        val _success =  db.delete(TABLE_NAME, "$COL_ID  =?" , arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    //update
    fun updateIncome(id: String, name: String, category: String, transaction: String, amount: String, frequency: String, days: String, date: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,id)
        values.put(COL_MEMBER_NAME,name)
        values.put(COL_TRANSACTION_CATEGORY,category)
        values.put(COL_TRANSACTION_NAME,transaction)
        values.put(COL_TRANSACTION_AMOUNT,amount)
        values.put(COL_TRANSACTION_FREQUENCY,frequency)
        values.put(COL_NO_OF_DAYS,days)
        values.put(COL_TRANSACTION_DATE,date)

        val _success = db.update(TABLE_NAME,values, "$COL_ID  =?",  arrayOf(id))
        return true
    }

    fun getLastInsertedId(): Int {
        var id = -1
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT last_insert_rowid()", null)
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0)
        }
        cursor.close()
        return id
    }

            //CRUD - IT21324406 - EXPENSE

    val allExpense : Cursor
        get() {
            val db = this.writableDatabase
            val rs = db.rawQuery("SELECT * FROM " + TABLE_NAME_EXPENSE, null)
            return rs
        }

    //insert
    fun addExpense(EX_name: String, EX_category: String, EX_transaction: String, EX_amount: String, EX_frequency: String, EX_days: String, EX_date: String) {
        val db = this.writableDatabase
        val vs = ContentValues()
        vs.put(MEMBER_NAME,EX_name)
        vs.put(TRANSACTION_CATEGORY,EX_category)
        vs.put(TRANSACTION_NAME,EX_transaction)
        vs.put(TRANSACTION_AMOUNT,EX_amount)
        vs.put(TRANSACTION_FREQUENCY,EX_frequency)
        vs.put(NO_OF_DAYS,EX_days)
        vs.put(TRANSACTION_DATE,EX_date)

        db.insert(TABLE_NAME_EXPENSE,null,vs)
    }

    //delete
    fun deleteExpense(_idX: String):Boolean{
        val db = this.writableDatabase
        val _successX =  db.delete(TABLE_NAME_EXPENSE, "$ID  =?" , arrayOf(_idX.toString())).toLong()
        db.close()
        return Integer.parseInt("$_successX") != -1
    }

    //update
    fun updateExpense(id1: String, EX_name: String, EX_category: String, EX_transaction: String, EX_amount: String, EX_frequency: String, EX_days: String, EX_date: String): Boolean {
        val db = this.writableDatabase
        val vs = ContentValues()
        vs.put(ID,id1)
        vs.put(MEMBER_NAME,EX_name)
        vs.put(TRANSACTION_CATEGORY, EX_category)
        vs.put(TRANSACTION_NAME,EX_transaction)
        vs.put(TRANSACTION_AMOUNT,EX_amount)
        vs.put(TRANSACTION_FREQUENCY, EX_frequency)
        vs.put(NO_OF_DAYS,EX_days)
        vs.put(TRANSACTION_DATE,EX_date)

        val _successX = db.update(TABLE_NAME_EXPENSE,vs, "$ID  =?",  arrayOf(id1))
        return true
    }

    fun getExpenseLastInsertedId(): Int {
        var id_EX = -1
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT last_insert_rowid()", null)
        if (cursor.moveToFirst()) {
            id_EX = cursor.getInt(0)
        }
        cursor.close()
        return id_EX
    }



                //AMANDI - CRUD

    fun insertData (
        salary: String,
        otherInc: String,
        rent: String,
        grocery: String,
        waterBill: String,
        electBill: String,
        eduExpense: String
    ) {
        val db = this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put(COL_2, salary)
        contentValues.put(COL_3, otherInc)
        contentValues.put(COL_4, rent)
        contentValues.put(COL_5, grocery)
        contentValues.put(COL_6, waterBill)
        contentValues.put(COL_7, electBill)
        contentValues.put(COL_8, eduExpense)
        db.insert(TABLE_NAME_FINANCIAL, null, contentValues)
    }

    //insert
    /*fun addIncome(name: String, category: String, transaction: String, amount: String, frequency: String, days: String, date: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_MEMBER_NAME,name)
        values.put(COL_TRANSACTION_CATEGORY,category)
        values.put(COL_TRANSACTION_NAME,transaction)
        values.put(COL_TRANSACTION_AMOUNT,amount)
        values.put(COL_TRANSACTION_FREQUENCY,frequency)
        values.put(COL_NO_OF_DAYS,days)
        values.put(COL_TRANSACTION_DATE,date)

        db.insert(TABLE_NAME,null,values)


    }*/

    fun updateData (id: String, salary: String, otherInc: String, rent: String, grocery: String, waterBill: String,electBill: String,eduExpense: String):
            Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, salary)
        contentValues.put(COL_3, otherInc)
        contentValues.put(COL_4, rent)
        contentValues.put(COL_5, grocery)
        contentValues.put(COL_6, waterBill)
        contentValues.put(COL_7, electBill)
        contentValues.put(COL_8, eduExpense)
        db.update (TABLE_NAME_FINANCIAL, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    fun deleteData (id: String): Int {
        val db= this.writableDatabase
        return db.delete (TABLE_NAME_FINANCIAL, "ID = ?", arrayOf(id))
    }

    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME_FINANCIAL", null)
        }

                //AMANDA - RGISTRATION - INSERT

    val allRegistrations : Cursor
           get() {
               val db = this.writableDatabase
               val res = db.rawQuery("SELECT * FROM " + TABLE_NAME_REGISTRATION, null)
               return res
           }

    //insert
    fun addRegistrations(name: String, phone: String, address: String, occupation: String, myself: String, family: String, username: String,password: String, question: String, answer: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_CUSTOMER_NAME,name)
        values.put(COL_CUSTOMER_PHONE,phone)
        values.put(COL_CUSTOMER_ADDRESS,address)
        values.put(COL_CUSTOMER_OCCUPATION,occupation)
        values.put(COL_MYSELF,myself)
        values.put(COL_FAMILY,family)
        values.put(COL_USERNAME,username)
        values.put(COL_PASSWORD,password)
        values.put(COL_SELECTED_QUESTION,question)
        values.put(COL_ANSWER,answer)

        db.insert(TABLE_NAME_REGISTRATION,null,values)


    }

    //delete
    fun deleteRegistrations(_id: String):Boolean{
        val db = this.writableDatabase
        val _success =  db.delete(TABLE_NAME_REGISTRATION, "$COL_ID1 =?" , arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }


    //update
    fun updateRegistrations(id: String, name: String, phone: String, address: String, occupation: String, myself: String, family: String, username: String,password: String, question: String, answer: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID1,id)
        values.put(COL_CUSTOMER_NAME,name)
        values.put(COL_CUSTOMER_PHONE,phone)
        values.put(COL_CUSTOMER_ADDRESS,address)
        values.put(COL_CUSTOMER_OCCUPATION,occupation)
        values.put(COL_MYSELF,myself)
        values.put(COL_FAMILY,family)
        values.put(COL_USERNAME,username)
        values.put(COL_PASSWORD,password)
        values.put(COL_SELECTED_QUESTION,question)
        values.put(COL_ANSWER,answer)

        val _success = db.update(TABLE_NAME_REGISTRATION,values, "$COL_ID1 =?",  arrayOf(id))
        return true
    }

    fun getLastInsertedIdReg(): Int {
        var id = -1
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT last_insert_rowid()", null)
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0)
        }
        cursor.close()
        return id
    }

                    //AMANDA - FAMILY -INSERT
     val allFamily : Cursor
       get() {
           val db = this.writableDatabase
           val res = db.rawQuery("SELECT * FROM " + TABLE_NAME_FAM, null)
           return res
       }

    //insert
    fun addFamily(children_num: String, adult_num : String, student_num: String, member_name : String, member_age : String, member_occupation : String, member_sclcom : String,transport: String, income: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_CHILDREN_NUMBER, children_num)
        values.put(COL_ADULTS_NUMBER, adult_num)
        values.put(COL_STUDENT_NUMBER, student_num)
        values.put(COL_MEMBER_NAME1, member_name)
        values.put(COL_MEMBER_AGE,member_age)
        values.put(COL_MEMBER_OCCUPATION,member_occupation)
        values.put(COL_MEMBER_SCLCOM, member_sclcom)
        values.put(COL_TRANSPORT, transport)
        values.put(COL_INCOME, income)

        db.insert(TABLE_NAME_FAM,null,values)


    }

    //delete
    fun deleteFamily(_id: String):Boolean{
        val db = this.writableDatabase
        val _success =  db.delete(TABLE_NAME_FAM, "$COL_ID2  =?" , arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }


    //update
    fun updateFamily(id: String, children_num: String, adult_num : String, student_num: String, member_name : String, member_age : String, member_occupation : String, member_sclcom : String,transport: String, income: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID2,id)
        values.put(COL_CHILDREN_NUMBER, children_num)
        values.put(COL_ADULTS_NUMBER, adult_num)
        values.put(COL_STUDENT_NUMBER, student_num)
        values.put(COL_MEMBER_NAME1, member_name)
        values.put(COL_MEMBER_AGE,member_age)
        values.put(COL_MEMBER_OCCUPATION,member_occupation)
        values.put(COL_MEMBER_SCLCOM, member_sclcom)
        values.put(COL_TRANSPORT, transport)
        values.put(COL_INCOME, income)

        val _success = db.update(TABLE_NAME_FAM,values, "$COL_ID  =?",  arrayOf(id))
        return true
    }

    fun getLastInsertedIdFam(): Int {
        var id = -1
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT last_insert_rowid()", null)
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0)
        }
        cursor.close()
        return id
    }

}
