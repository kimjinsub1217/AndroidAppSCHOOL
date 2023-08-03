package com.example.mini02_boardproject01.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel(): ViewModel() {
    var userId = MutableLiveData<String>()
    var userPw=MutableLiveData<String>()
    var userPw2=MutableLiveData<String>()

    fun reset(){
        userId.value = ""
        userPw.value = ""
        userPw2.value = ""
    }

}