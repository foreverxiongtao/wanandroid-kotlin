package com.example.mvvmproject.net

import com.android.example.github.util.LiveDataCallAdapterFactory
import com.example.mvvmproject.App
import com.example.mvvmproject.constant.RetrofitConstant
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager {


    private lateinit var retrofit: Retrofit

    companion object {
        fun getInstance(): RetrofitManager {
            return RetrofitManagerHolder.holder
        }
    }

    private object RetrofitManagerHolder {
        val holder = RetrofitManager()
    }


    fun getRetrofit(): Retrofit {
        return retrofit
    }

    fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }


    private fun initRetrofit() {
        val okHttpClient =
            OkHttpClient.Builder().writeTimeout(RetrofitConstant.WRITE_TIME_OUT_DEFAULT, TimeUnit.MILLISECONDS)
                .readTimeout(RetrofitConstant.READ_TIME_OUT_DEFAULT, TimeUnit.MILLISECONDS)
                .connectTimeout(RetrofitConstant.CONNECT_TIME_OUT_DEFAULT, TimeUnit.MILLISECONDS)
                .addInterceptor(ChuckInterceptor(App.getApp().applicationContext))
                .build()
        val builder: Retrofit.Builder = Retrofit.Builder()
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.addCallAdapterFactory(LiveDataCallAdapterFactory())
        builder.baseUrl(RetrofitConstant.HTTP_BASE_URL)
        builder.client(okHttpClient)
        retrofit = builder.build()
    }

    init {
        initRetrofit()
    }
}