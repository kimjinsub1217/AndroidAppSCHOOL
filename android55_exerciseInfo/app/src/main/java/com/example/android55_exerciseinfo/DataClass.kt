package com.example.android55_exerciseinfo

class DataClass {
    // 운동부 정보 클래스
    companion object {
        val exerciseList = mutableListOf<Any>()
    }
}

data class BaseballClass(
    var name: String,
    var battingAverage: Int,
    var homeRunCount: Int,
    var safety: Int
)

data class SoccerClass(
    var name: String,
    var goalCount: Int,
    var assistCount: Int,
)

data class SwimmingClass(
    var name: String,
    var how_swim: String,
)