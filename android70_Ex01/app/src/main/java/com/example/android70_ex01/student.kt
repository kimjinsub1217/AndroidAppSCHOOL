package com.example.android70_ex01

class student {
    companion object{
        // 학생의 정보를 담을 리스트
        var studentList = mutableListOf<StudentClass>()
    }
}

data class StudentClass(var idx: Int, var nameData: String, var ageData: Int, var koreanData: Int)