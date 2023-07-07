package com.example.android70_ex03.db

class Password {
    companion object {
        var passwordList = mutableListOf<PasswordClass>()
    }
}

data class PasswordClass(var idx: Int, var passwordData: String)