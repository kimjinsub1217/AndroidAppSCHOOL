package com.example.mini02_boardproject01.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mini02_boardproject01.MainActivity
import com.example.mini02_boardproject01.PostListFragment
import com.example.mini02_boardproject01.repository.RepositoryPostList

class ViewModelPostList()  : ViewModel() {
    var dataList = MutableLiveData<MutableList<TestData>>()
//    val tempList = mutableListOf<TestData>()

    init {
        getAll()
    }

//    fun addItem(testData: TestData) {
//        tempList.add(testData)
//        dataList.value = tempList
//    }

    fun getAll() {
        // Repository로 부터 데이터를 가져와 설정한다.
        dataList.value = RepositoryPostList.getAll(MainActivity.mainActivity)
    }
}

data class TestData(
    var idx: Long, var data1: String, var data2: String
)
