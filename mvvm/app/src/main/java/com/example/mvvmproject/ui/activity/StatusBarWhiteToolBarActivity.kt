package com.example.mvvmproject.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import com.example.mvvmproject.R
import com.example.mvvmproject.base.activity.BaseActivity
import com.example.mvvmproject.utils.statusbar.Eyes


class StatusBarWhiteToolBarActivity : BaseActivity() {
    override fun initParams(intent: Intent) {
    }

    override fun bindView(): View {
        return  mLayoutInflator.inflate(R.layout.activity_login,null)
//       return View.inflate(this,R.layout.activity_login,null)
    }

    override fun initView(rootView: View) {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        //        getSupportActionBar().setTitle("StatusBar white with toolbar");

        Eyes.setStatusBarLightMode(this, Color.WHITE)
    }

    override fun initData() {

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_statusbar_white_toolbar)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)
//        //        getSupportActionBar().setTitle("StatusBar white with toolbar");
//
//        Eyes.setStatusBarLightMode(this, Color.WHITE)
//    }
}
