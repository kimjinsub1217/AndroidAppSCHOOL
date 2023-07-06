package com.example.android70_ex01

class student {
    companion object {
        var memoList = mutableListOf<MemoClass>()
    }
}

data class MemoClass(var idx: Int, var titleData: String, var descriptionData: String, var dateData: String)