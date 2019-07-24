package com.example.mvvmproject.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.android.example.github.AppExecutors
import com.example.mvvmproject.api.API
import com.example.mvvmproject.api.ApiResponse
import com.example.mvvmproject.model.entity.LoginResponse
import com.example.mvvmproject.model.entity.Resource
import com.example.mvvmproject.model.entity.Result
import com.example.mvvmproject.model.entity.Status
import com.example.mvvmproject.net.NetWorkHandler
import com.example.mvvmproject.net.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterModelImpl : RegisterModel {
    override fun login(username: String, password: String): LiveData<Resource<Result.Data<LoginResponse>>> {

        return object :
            NetWorkHandler<Result.Data<LoginResponse>, Result.Data<LoginResponse>>(AppExecutors.getInstance()) {
            override fun shouldFetch(): Boolean {
                return true
            }

            override fun createCall(): LiveData<ApiResponse<Result.Data<LoginResponse>>> {
                return RetrofitManager.getInstance().getService(API::class.java).loginWanAndroid(username, password)
            }
        }.asLiveData()
    }

    override fun register(
        username: String,
        password: String,
        repassword: String,
        liveData: MyLiveData<LoginResponse>
    ) {
        RetrofitManager.getInstance().getService(API::class.java).registerWanAndroid(username, password, repassword)
            .enqueue(
                object : MutableLiveData<LoginResponse>(), Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                        val loginResponse = LoginResponse()
//                        loginResponse.status = Status.Error
                        liveData.postValue(loginResponse)
                    }

                    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                        response?.let {
                            val body = it.body()
                            liveData.postValue(body)
                        }
                    }
                })
    }

}