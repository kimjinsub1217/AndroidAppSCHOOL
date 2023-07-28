package com.example.mini02_boardproject01

class SearchList {
    companion object {
        var ListSearch = mutableListOf<SearchListClass>()
    }
}

data class SearchListClass(
    var idx: Int,
    var title: String,
    var details: String
)

