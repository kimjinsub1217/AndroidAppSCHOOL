package com.example.android55_zooinfo

class DataClass {
    companion object{
        // 동물 정보 클래스
        val animalList = mutableListOf<ZooClass>()
    }
}

data class ZooClass(var type:String, var name:String, var age:Int,var weight:Int)