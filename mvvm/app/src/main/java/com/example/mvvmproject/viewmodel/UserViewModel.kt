package com.example.mvvmproject.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import android.util.Log
import com.example.mvvmproject.model.MyLiveData
import com.example.mvvmproject.model.RegisterModel
import com.example.mvvmproject.model.RegisterModelImpl
import com.example.mvvmproject.model.entity.LoginResponse
import com.example.mvvmproject.model.entity.Resource
import com.example.mvvmproject.model.entity.Result

class UserViewModel : ViewModel() {
    private val mRegisterModel: RegisterModel by lazy {
        RegisterModelImpl()
    }

    private val mLiveData = MyLiveData<LoginResponse>()

    fun addObserver(lifecycleOwner: LifecycleOwner, observer: Observer<LoginResponse>) {
        mLiveData.observe(lifecycleOwner, observer)
    }

    fun register(username: String, password: String, repassword: String):LiveData<Resource<Result.Data<LoginResponse>>> {
        return  mRegisterModel.register(username, password, repassword)
    }

    fun login(username: String, password: String): LiveData<Resource<Result.Data<LoginResponse>>> {
        return mRegisterModel.login(username, password)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("UserViewModel", "onCleared")
    }
}