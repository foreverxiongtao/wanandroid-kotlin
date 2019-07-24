package com.example.mvvmproject.model.entity

class LoginResponse : Result.Data<LoginResponse>() {
    var id: Int = -1
    var username: String? = null
    var password: String? = null
    var icon: String? = null
    var type: Int = -1
    var collectIds: List<Int>? = null
}