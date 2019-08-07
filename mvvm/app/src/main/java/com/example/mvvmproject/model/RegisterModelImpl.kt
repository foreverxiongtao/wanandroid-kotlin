package com.example.mvvmproject.model

import androidx.lifecycle.LiveData
import com.android.example.github.AppExecutors
import com.example.mvvmproject.api.API
import com.example.mvvmproject.api.ApiResponse
import com.example.mvvmproject.model.entity.LoginResponse
import com.example.mvvmproject.model.entity.Resource
import com.example.mvvmproject.model.entity.Result
import com.example.mvvmproject.net.NetWorkHandler
import com.example.mvvmproject.net.RetrofitManager

class RegisterModelImpl : RegisterModel {
    override fun login(username: String, password: String): LiveData<Resource<Result.Data<LoginResponse>>> {

        return object :
            NetWorkHandler<Result.Data<LoginResponse>>(AppExecutors.getInstance()) {
            override fun createCall(): LiveData<ApiResponse<Result.Data<LoginResponse>>> {
                return RetrofitManager.getInstance().getService(API::class.java).loginWanAndroid(username, password)
            }
        }.asLiveData()
    }

    override fun register(
        username: String,
        password: String,
        repassword: String
    ): LiveData<Resource<Result.Data<LoginResponse>>> {

        return object :
            NetWorkHandler<Result.Data<LoginResponse>>(AppExecutors.getInstance()) {
            override fun createCall(): LiveData<ApiResponse<Result.Data<LoginResponse>>> {
                return RetrofitManager.getInstance().getService(API::class.java)
                    .registerWanAndroid(username, password, repassword)
            }
        }.asLiveData()
    }

}