package com.example.mvvmproject.model.entity

open class Result {
    var errorCode: Int? = null
    var errorMsg: String? = null

    open class Data<T> : Result() {
        var data: T? = null
    }
}