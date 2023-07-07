package com.example.android70_ex03.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelperPassword(context: Context) : SQLiteOpenHelper(context, "password.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table TestTable
            (idx integer primary key,
            passwordData text not null)
        """.trimIndent()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}