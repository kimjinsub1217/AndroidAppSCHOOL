package com.example.android70_ex03.db

import android.content.Context

class DAOPassword {
    companion object {

        fun insertData(context: Context, data: PasswordClass) {

            val sql = """insert into TestTable
                | (passwordData)
                | values (?)
            """.trimMargin()


            val arg1 = arrayOf(
                data.passwordData
            )

            val sqliteDatabase = DBHelperPassword(context)

            sqliteDatabase.writableDatabase.execSQL(sql, arg1)

            sqliteDatabase.close()
        }


        fun selectData(context: Context, idx: Int): PasswordClass? {
            val sql = "select * from TestTable where idx=?"

            val arg1 = arrayOf("$idx")

            val dbHelper = DBHelperPassword(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("passwordData")

            val idx = cursor.getInt(idx1)
            val passwordData = cursor.getString(idx2)

            val testClass = PasswordClass(idx, passwordData)

            dbHelper.close()
            return testClass

        }


        fun selectAllData(context: Context): MutableList<PasswordClass> {

            val sql = "select * from TestTable"


            val dbHelper = DBHelperPassword(context)

            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            val dataList = mutableListOf<PasswordClass>()

            while (cursor.moveToNext()) {

                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("passwordData")

                val idx = cursor.getInt(idx1)
                val passwordData = cursor.getString(idx2)

                val testClass = PasswordClass(idx, passwordData)
                dataList.add(testClass)
            }
            dbHelper.close()
            return dataList
        }

        fun updateData(context: Context, obj: PasswordClass) {

            val sql = """update TestTable
                | set passwordData=?
                | where idx=?
            """.trimMargin()


            val args = arrayOf(obj.passwordData, obj.idx)

            val dbHelper = DBHelperPassword(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        fun deleteData(context: Context, idx: Int) {
            // 쿼리문
            // TestTable에서 idx가 ? 인 행을 삭제한다.
            val sql = "delete from TestTable where idx = ?"
            // ?에 들어갈 값
            val args = arrayOf(idx)
            // 쿼리 실행
            val dbHelper = DBHelperPassword(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        fun deleteAllData(context: Context) {
            val sql = "DELETE FROM TestTable"

            val dbHelper = DBHelperPassword(context)
            dbHelper.writableDatabase.execSQL(sql)
            dbHelper.close()
        }
    }
}