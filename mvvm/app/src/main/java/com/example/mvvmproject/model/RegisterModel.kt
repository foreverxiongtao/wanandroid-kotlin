package com.example.mvvmproject.model

import androidx.lifecycle.LiveData
import com.example.mvvmproject.api.ApiResponse
import com.example.mvvmproject.model.entity.LoginResponse
import com.example.mvvmproject.model.entity.Resource
import com.example.mvvmproject.model.entity.Result

/**
 *    author : desperado
 *    e-mail : foreverxiongtao@sina.com
 *    date   : 2019/6/25 上午11:13
 *    desc   : 注册model层
 *    version: 1.0
 */
interface RegisterModel {
    //注册
    fun register(username: String, password: String, repassword: String): LiveData<Resource<Result.Data<LoginResponse>>>

    //登陆
    fun login(username: String, password: String): LiveData<Resource<Result.Data<LoginResponse>>>
}