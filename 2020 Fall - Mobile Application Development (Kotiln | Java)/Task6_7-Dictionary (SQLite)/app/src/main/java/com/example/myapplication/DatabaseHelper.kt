package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable =
            "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " ITEM1 TEXT, ITEM2 TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addData(item1: String?, item2: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL2, item1)
        contentValues.put(COL3, item2)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun getListContents(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COL2", null)
    }

    companion object {
        const val DATABASE_NAME = "mylist.db"
        const val TABLE_NAME = "mylist_data"
        const val COL1 = "ID"
        const val COL2 = "ITEM1"
        const val COL3 = "ITEM2"
    }
}