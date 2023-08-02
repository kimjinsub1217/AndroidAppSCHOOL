package com.example.mini02_boardproject01.repository

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.mini02_boardproject01.vm.TestData
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RepositoryPostList {

    companion object {


        fun addData(context: Context, testData: TestData) {

            val database = FirebaseDatabase.getInstance()
            val testDataRef = database.getReference("data")
            testDataRef.push().setValue(testData)
        }

        fun getAll(context: Context): MutableList<TestData> {
            val tempList = mutableListOf<TestData>()

            val database = FirebaseDatabase.getInstance()
            // TestData에 접근한다.
            val testDataRef = database.getReference("data")

            // 전부를 가져온다.
            testDataRef.get().addOnCompleteListener {

                // 가져온 데이터의 수 만큼 반복한다.
                for (a1 in it.result.children) {
                    // 데이터를 가져와서 타입을 변환하되, Safe Call과 Elvis 연산자를 사용하여 기본값을 제공
                    val data1 = a1.child("idx").value as? Long ?: 0L
                    val data2 = a1.child("data1").value as? String ?: ""
                    val data3 = a1.child("data2").value as? String ?: ""
                    val t1 = TestData(data1, data2, data3)
                    Log.i("아아아아아",t1.toString())
                    tempList.add(t1)
                }
            }
            return tempList
        }
    }
}