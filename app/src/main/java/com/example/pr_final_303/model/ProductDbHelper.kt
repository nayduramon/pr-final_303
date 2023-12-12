package com.example.pr_final_303.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductDbHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Productos.db"
    }

    private val SQL_CREATE_ENTRIES = """
        CREATE TABLE ${ProductContract.TABLE_NAME} (
            ${ProductContract.COLUMN_ID} INTEGER PRIMARY KEY,
            ${ProductContract.COLUMN_NAME} TEXT,
            ${ProductContract.COLUMN_PRICE} REAL
        )
    """.trimIndent()

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ProductContract.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}