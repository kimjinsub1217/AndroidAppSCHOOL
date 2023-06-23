package com.example.android47_ex0answer

class DataClass {
    companion object {
        val humanList = mutableListOf<HumanInfo>()
    }
}

data class HumanInfo(
    var name: String,
    var date: String,
    var gender: String,
    var hobbyList: MutableList<String>
)