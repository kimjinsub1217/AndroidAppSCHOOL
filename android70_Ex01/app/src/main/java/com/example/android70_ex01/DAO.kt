package com.example.android70_ex01

import android.content.Context

class DAO {
    companion object {

        fun insertData(context: Context, data: StudentClass) {

            val sql = """insert into TestTable
                | (nameData, ageData, koreanData)
                | values (?, ?, ?)
            """.trimMargin()


            val arg1 = arrayOf(
                data.nameData, data.ageData, data.koreanData
            )

            val sqliteDatabase = DBHelper(context)

            sqliteDatabase.writableDatabase.execSQL(sql, arg1)

            sqliteDatabase.close()
        }


        fun selectData(context: Context, idx: Int): StudentClass {

            val sql = "select * from TestTable where idx=?"

            val arg1 = arrayOf("$idx")


            val dbHelper = DBHelper(context)

            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()


            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("nameData")
            val idx3 = cursor.getColumnIndex("ageData")
            val idx4 = cursor.getColumnIndex("koreanData")

            val idx = cursor.getInt(idx1)
            val nameData = cursor.getString(idx2)
            val ageData = cursor.getInt(idx3)
            val koreanData = cursor.getInt(idx4)

            val testClass = StudentClass(idx, nameData, ageData, koreanData)

            dbHelper.close()
            return testClass
        }


        fun selectAllData(context: Context): MutableList<StudentClass> {

            val sql = "select * from TestTable"


            val dbHelper = DBHelper(context)

            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            val dataList = mutableListOf<StudentClass>()

            while (cursor.moveToNext()) {

                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("nameData")
                val idx3 = cursor.getColumnIndex("ageData")
                val idx4 = cursor.getColumnIndex("koreanData")

                val idx = cursor.getInt(idx1)
                val nameData = cursor.getString(idx2)
                val ageData = cursor.getInt(idx3)
                val koreanData = cursor.getInt(idx4)

                val testClass = StudentClass(idx, nameData, ageData, koreanData)
                dataList.add(testClass)
            }
            dbHelper.close()
            return dataList
        }

        fun updateData(context: Context, obj: StudentClass) {

            val sql = """update TestTable
                | set textData=?, intData=?, doubleData=?, dateData=?
                | where idx=?
            """.trimMargin()


            val args = arrayOf(obj.nameData, obj.ageData, obj.koreanData, obj.idx)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        fun deleteData(context: Context, idx: Int) {

            val sql = "delete from TestTable where idx = ?"

            val args = arrayOf(idx)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }
}