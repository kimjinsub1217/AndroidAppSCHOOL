package com.example.android70_ex03.db

class Memo {
    companion object {
        var memoList = mutableListOf<MemoClass>()
    }
}
data class MemoClass(
    var idx: Int,
    var memoTitleData: String,
    var descriptionData: String,
    var dateData: String,
    var categoryData:Int
)