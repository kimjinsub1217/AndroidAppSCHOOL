package com.example.android47_ex01

object MyDataList {
    var dataList = mutableListOf<DataClass>()
}

data class DataClass(var name: String, var date: String, var gender: String, var hobby: String)