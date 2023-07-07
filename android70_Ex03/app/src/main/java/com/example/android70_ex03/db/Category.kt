package com.example.android70_ex03.db

class Category {
    companion object {
        var categoryList = mutableListOf<CategoryClass>()
    }
}

data class CategoryClass(
    var idx: Int,
    var titleData: String,
    var memoList: MutableList<MemoClass> = mutableListOf()
)

