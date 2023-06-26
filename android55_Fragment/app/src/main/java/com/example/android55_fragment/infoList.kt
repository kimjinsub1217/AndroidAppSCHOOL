package com.example.android55_fragment


object infoList{
    var dataList = mutableListOf<DataClass>()
}



data class DataClass(var name: String, var age: Int, var kor: Int)
