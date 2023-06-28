package com.example.android60_ex01

class DataClass {
    companion object{
        // 동물 정보 클래스
        val studentList = mutableListOf<StudentClass>()
    }
}

data class StudentClass(var name:String, var age:Int,var kor:Int)