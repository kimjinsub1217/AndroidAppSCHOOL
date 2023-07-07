package com.example.android70_ex03.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelperMemo(context: Context) : SQLiteOpenHelper(context, "Memo.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table TestTable
            (idx integer primary key,
            memoTitleData text not null,          
            descriptionData text not null,
            dateData date not null,
            categoryData integer not null)
        """.trimIndent()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}