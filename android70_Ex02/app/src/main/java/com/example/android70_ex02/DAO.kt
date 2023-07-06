package com.example.android70_ex02

import android.content.Context
import com.example.android70_ex01.MemoClass

class DAO {
    companion object {

        fun insertData(context: Context, data: MemoClass) {

            val sql = """insert into TestTable
                | (titleData, DescriptionData, dateData)
                | values (?, ?, ?)
            """.trimMargin()


            val arg1 = arrayOf(
                data.titleData, data.descriptionData, data.dateData
            )

            val sqliteDatabase = DBHelper(context)

            sqliteDatabase.writableDatabase.execSQL(sql, arg1)

            sqliteDatabase.close()
        }


        fun selectData(context: Context, idx: Int): MemoClass? {
            val sql = "select * from TestTable where idx=?"

            val arg1 = arrayOf("$idx")

            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("titleData")
            val idx3 = cursor.getColumnIndex("DescriptionData")
            val idx4 = cursor.getColumnIndex("dateData")

            val idx = cursor.getInt(idx1)
            val titleData = cursor.getString(idx2)
            val descriptionData = cursor.getString(idx3)
            val dateData = cursor.getString(idx4)

            val testClass = MemoClass(idx, titleData, descriptionData, dateData)

            dbHelper.close()
            return testClass

        }


        fun selectAllData(context: Context): MutableList<MemoClass> {

            val sql = "select * from TestTable"


            val dbHelper = DBHelper(context)

            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            val dataList = mutableListOf<MemoClass>()

            while (cursor.moveToNext()) {

                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("titleData")
                val idx3 = cursor.getColumnIndex("DescriptionData")
                val idx4 = cursor.getColumnIndex("dateData")

                val idx = cursor.getInt(idx1)
                val titleData = cursor.getString(idx2)
                val DescriptionData = cursor.getString(idx3)
                val dateData = cursor.getString(idx4)

                val testClass = MemoClass(idx, titleData, DescriptionData, dateData)
                dataList.add(testClass)
            }
            dbHelper.close()
            return dataList
        }

        fun updateData(context: Context, obj: MemoClass) {

            val sql = """update TestTable
                | set titleData=?, DescriptionData=?, dateData=?
                | where idx=?
            """.trimMargin()


            val args = arrayOf(obj.titleData, obj.descriptionData, obj.dateData, obj.idx)

            val dbHelper = DBHelper(context)
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
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        fun deleteAllData(context: Context) {
            val sql = "DELETE FROM TestTable"

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql)
            dbHelper.close()
        }
    }
}