package com.example.mvvmproject.model

import androidx.lifecycle.MutableLiveData

class MyLiveData<T> : MutableLiveData<T>() {
    override fun postValue(value: T) {
        super.postValue(value)
    }

    override fun setValue(value: T) {
        super.setValue(value)
    }
}