package com.example.androidhomework1

object memeoList {
    val myMemoList: MutableMap<String, MutableList<MemoClass>> = mutableMapOf()

    data class MemoClass(var name: String, var detail: String)
}