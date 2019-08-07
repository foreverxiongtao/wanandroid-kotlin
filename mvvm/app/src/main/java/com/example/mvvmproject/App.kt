package com.example.mvvmproject

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.mvvmproject.utils.AppUtils

class App : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        AppUtils.init(this)
    }


    companion object {
        private lateinit var mApp: App
        fun getApp(): App {
            return mApp
        }
    }
}