package com.example.mvvmproject.api

import androidx.lifecycle.LiveData
import com.example.mvvmproject.model.entity.LoginResponse
import com.example.mvvmproject.model.entity.Resource
import com.example.mvvmproject.model.entity.Result
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {


    /**
     * 登录
     * @param username username
     * @param password password
     * @return Deferred<LoginResponse>
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun loginWanAndroid(
        @Field("username") username: String,
        @Field("password") password: String
    ): LiveData<ApiResponse<Result.Data<LoginResponse>>>

    /**
     * 注册
     * @param username username
     * @param password password
     * @param repassword repassword
     * @return Deferred<LoginResponse>
     */
    @POST("/user/register")
    @FormUrlEncoded
    fun registerWanAndroid(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassowrd: String
    ): LiveData<ApiResponse<Result.Data<LoginResponse>>>
}