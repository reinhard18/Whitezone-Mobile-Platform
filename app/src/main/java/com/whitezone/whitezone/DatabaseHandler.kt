package com.whitezone.whitezone

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.whitezone.whitezone.Model.ModelHabit

class DatabaseHandler (context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

            companion object{
                private const val DATABASE_VERSION = 1
                private const val DATABASE_NAME = "HabitDatabase"
                private const val TABLE_HABITS = "HabitTable"

                private const val KEY_ID = "_id"
                private const val KEY_NAME = "habit_name"
                private const val KEY_DATE = "date"

            }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_HABITS_TABLE = ("CREATE TABLE " + TABLE_HABITS +"(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_DATE + "TEXT" + ")" )
        db?.execSQL(CREATE_HABITS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS "+  TABLE_HABITS)
        onCreate(db)

    }

    fun addHabit(hab : ModelHabit) : Long{
        val db = this.writableDatabase

        val contentValues= ContentValues()
        contentValues.put(KEY_NAME, hab.habit_name)
        contentValues.put(KEY_DATE, hab.date)

        val success = db.insert(TABLE_HABITS, null, contentValues)

        db.close()
        return  success

    }

    fun viewHabit(): ArrayList<ModelHabit>{
        val habList: ArrayList<ModelHabit> = ArrayList<ModelHabit>()
        val selectQuery = "SELECT * FROM $TABLE_HABITS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery,null)
        } catch (e : SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var date: String

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                date = cursor.getString(cursor.getColumnIndex(KEY_DATE))

                val hab = ModelHabit(id = id, habit_name= name,date = date)
            }while(cursor.moveToNext())
        }
        return habList
    }

    fun deleteHabit(hab: ModelHabit): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, hab.id)

        val success = db.delete(TABLE_HABITS, KEY_ID + hab.id, null)

        db.close()
        return  success
    }
}