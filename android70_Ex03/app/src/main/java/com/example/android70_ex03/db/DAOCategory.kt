package com.example.android70_ex03.db

import android.content.Context

class DAOCategory {
    companion object {

        fun insertData(context: Context, data: CategoryClass) {

            val sql = """insert into TestTable
                | (titleData)
                | values (?)
            """.trimMargin()

            val arg1 = arrayOf(
                data.titleData
            )

            val sqliteDatabase = DBHelperCategory(context)
            sqliteDatabase.writableDatabase.execSQL(sql, arg1)
            sqliteDatabase.close()
        }


        fun selectData(context: Context, idx: Int): CategoryClass? {

            val sql = "select * from TestTable where idx=?"
            val arg1 = arrayOf("$idx")

            val dbHelper = DBHelperCategory(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("titleData")


            val idx = cursor.getInt(idx1)
            val titleData = cursor.getString(idx2)


            val testClass = CategoryClass(idx, titleData)

            dbHelper.close()
            return testClass
        }


        fun selectAllData(context: Context): MutableList<CategoryClass> {

            val sql = "select * from TestTable"
            val dbHelper = DBHelperCategory(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            val dataList = mutableListOf<CategoryClass>()

            while (cursor.moveToNext()) {

                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("titleData")

                val idx = cursor.getInt(idx1)
                val titleData = cursor.getString(idx2)

                val testClass =
                    CategoryClass(idx, titleData)
                dataList.add(testClass)
            }
            dbHelper.close()
            return dataList
        }

        fun updateData(context: Context, obj: CategoryClass) {

            val sql = """update TestTable
                | set titleData=?
                | where idx=?
            """.trimMargin()
            val args = arrayOf(
                obj.titleData,
                obj.idx.toString()
            )

            val dbHelper = DBHelperCategory(context)
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
            val dbHelper = DBHelperCategory(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        fun deleteAllData(context: Context) {
            val sql = "DELETE FROM TestTable"

            val dbHelper = DBHelperCategory(context)
            dbHelper.writableDatabase.execSQL(sql)
            dbHelper.close()
        }
    }
}