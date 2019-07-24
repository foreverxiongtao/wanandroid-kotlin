package com.example.mvvmproject.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.mvvmproject.R
import com.example.mvvmproject.base.activity.BaseActivity
import com.example.mvvmproject.ui.activity.LoginActivity
import com.example.mvvmproject.ui.activity.RegisterActivity
import com.example.mvvmproject.utils.startActivityForClass
import com.example.mvvmproject.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val userViewModel: UserViewModel by lazy {
        ViewModelProviders.of(MainActivity@ this).get(UserViewModel::class.java)
    }

    override fun initData() {

    }

    override fun initParams(intent: Intent) {
    }

    override fun bindView(): View {
        return mLayoutInflator.inflate(R.layout.activity_main, null, false)
    }

    override fun initView(rootView: View) {

    }

    fun setValue(view:View){
        startActivityForClass(LoginActivity::class.java)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
    }

}