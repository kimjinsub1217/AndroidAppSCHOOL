package com.example.android70_ex03.db

import android.content.Context

class DAOMemo {
    companion object {

        fun insertData(context: Context, data: MemoClass) {

            val sql = """insert into TestTable
                | (memoTitleData, descriptionData, dateData, categoryData)
                | values (?, ?, ?, ?)
            """.trimMargin()

            val arg1 = arrayOf(
                data.memoTitleData, data.descriptionData, data.dateData, data.categoryData
            )

            val sqliteDatabase = DBHelperMemo(context)
            sqliteDatabase.writableDatabase.execSQL(sql, arg1)
            sqliteDatabase.close()
        }


        fun selectData(context: Context, idx: Int): MemoClass? {

            val sql = "select * from TestTable where idx=?"
            val arg1 = arrayOf("$idx")

            val dbHelper = DBHelperMemo(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("memoTitleData")
            val idx3 = cursor.getColumnIndex("descriptionData")
            val idx4 = cursor.getColumnIndex("dateData")
            val idx5 = cursor.getColumnIndex("categoryData")

            val idx = cursor.getInt(idx1)
            val memoTitleData = cursor.getString(idx2)
            val descriptionData = cursor.getString(idx3)
            val dateData = cursor.getString(idx4)
            val categoryData = cursor.getInt(idx5)

            val testClass = MemoClass(idx, memoTitleData, descriptionData, dateData, categoryData)

            dbHelper.close()
            return testClass
        }


        fun selectAllData(context: Context): MutableList<MemoClass> {

            val sql = "select * from TestTable"
            val dbHelper = DBHelperMemo(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            val dataList = mutableListOf<MemoClass>()

            while (cursor.moveToNext()) {

                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("memoTitleData")
                val idx3 = cursor.getColumnIndex("descriptionData")
                val idx4 = cursor.getColumnIndex("dateData")
                val idx5 = cursor.getColumnIndex("categoryData")

                val idx = cursor.getInt(idx1)
                val memoTitleData = cursor.getString(idx2)
                val descriptionData = cursor.getString(idx3)
                val dateData = cursor.getString(idx4)
                val categoryData = cursor.getInt(idx5)

                val testClass =
                    MemoClass(idx, memoTitleData, descriptionData, dateData, categoryData)
                dataList.add(testClass)
            }
            dbHelper.close()
            return dataList
        }

        fun updateData(context: Context, obj: MemoClass) {

            val sql = """update TestTable
                | set memoTitleData=?, descriptionData=?, dateData=?, categoryData=?
                | where idx=?
            """.trimMargin()
            val args = arrayOf(
                obj.memoTitleData,
                obj.descriptionData,
                obj.dateData,
                obj.categoryData,
                obj.idx.toString()

            )

            val dbHelper = DBHelperMemo(context)
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
            val dbHelper = DBHelperMemo(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        fun deleteAllData(context: Context) {
            val sql = "DELETE FROM TestTable"

            val dbHelper = DBHelperMemo(context)
            dbHelper.writableDatabase.execSQL(sql)
            dbHelper.close()
        }
    }
}