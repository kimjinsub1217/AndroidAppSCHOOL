package com.example.mini02_boardproject01

class SearchList {
    companion object {
        var ListSearch = mutableListOf<DataModel>()
    }
}

data class DataModel(
    val value1: String,
    val value2: String
)

