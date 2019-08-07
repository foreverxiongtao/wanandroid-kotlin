package com.example.mvvmproject.base.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmproject.R
import com.example.mvvmproject.utils.dip2px
import com.example.mvvmproject.utils.statusbar.Eyes
import com.github.ybq.android.spinkit.SpinKitView
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 *    author : desperado
 *    e-mail : foreverxiongtao@sina.com
 *    date   : 2019/6/24 下午7:59
 *    desc   : base activity
 *    version: 1.0
 */
open abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mParentView: View
    protected val mLayoutInflator: LayoutInflater
        get() = LayoutInflater.from(this@BaseActivity)


    private val mSpintView: SpinKitView by lazy {
        val view: SpinKitView = mLayoutInflator.inflate(R.layout.view_loading, null, false) as SpinKitView
        return@lazy view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams(intent)
        mParentView = bindView()
        if (mParentView != null) {
            setContentView(mParentView)
            Eyes.setStatusBarLightMode(this, Color.WHITE)
            initView(mParentView)
            initData()
        }
    }

    abstract fun initParams(intent: Intent)
    abstract fun bindView(): View
    abstract fun initView(rootView: View)
    abstract fun initData()

    fun showLoadingDialog(visable: Boolean) {
        if (mSpintView.parent == null) {
            val layoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.topMargin = dip2px(this, 100F)
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
            mSpintView.layoutParams = layoutParams
            (mParentView as? ViewGroup)?.addView(mSpintView)
        }
        if (visable) {
            mSpintView.visibility = View.VISIBLE
        } else {
            mSpintView.visibility = View.GONE
        }
    }

    fun initTitleBar(toolbar: Toolbar, title: String) {
        tv_toolbar_title.text = title
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationIcon(R.mipmap.icon_arrow_back)
        toolbar.setNavigationOnClickListener { finish() }
    }

    fun setTitle(title: String){
        tv_toolbar_title.text = title
    }
}