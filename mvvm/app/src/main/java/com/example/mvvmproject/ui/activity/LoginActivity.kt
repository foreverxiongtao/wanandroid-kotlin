package com.example.mvvmproject.ui.activity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.util.Log
import android.view.View
import com.example.mvvmproject.R
import com.example.mvvmproject.base.activity.BaseActivity
import com.example.mvvmproject.constant.Constant
import com.example.mvvmproject.model.entity.LoginResponse
import com.example.mvvmproject.model.entity.Resource
import com.example.mvvmproject.model.entity.Result
import com.example.mvvmproject.model.entity.Status
import com.example.mvvmproject.utils.showToast
import com.example.mvvmproject.utils.startActivityForClass
import com.example.mvvmproject.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    private val mUserViewModel: UserViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                handleLogin()
            }
            R.id.btn_login_register -> {
                startActivityForClass(RegisterActivity::class.java)
            }
        }
    }

    override fun initParams(intent: Intent) {

    }

    override fun bindView(): View {
        return mLayoutInflator.inflate(R.layout.activity_login, null)
    }

    override fun initView(rootView: View) {
        initTitleBar(toolbar, resources.getString(R.string.str_login))
        btn_login_register.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }

    override fun initData() {

    }


    private fun handleLogin() {
        val nickName = et_login_nick.text.toString()
        val pwd = et_login_pwd.text.toString()
        if (nickName.isEmpty()) {
            showToast(getString(R.string.str_login_nick_empty))
            return
        }
        if (pwd.isEmpty()) {
            showToast(getString(R.string.str_login_pwd_error))
            return
        }
        mUserViewModel.login(nickName, pwd).observe(this, object : Observer<Resource<Result.Data<LoginResponse>>> {
            override fun onChanged(t: Resource<Result.Data<LoginResponse>>?) {
                t?.let {
                    when (it.status) {
                        Status.ERROR -> {
                            showLoadingDialog(false)
                            showToast(R.string.str_net_error)
                        }
                        Status.SUCCESS -> {
                            showLoadingDialog(false)
                            val content = t.content
                            content?.let { content ->
                                when (content.errorCode) {
                                    Constant.RESPONSE_SUCCESS -> {
                                        Log.d("onChanged", "onChanged")
                                        showToast(R.string.str_login_suceess)
                                    }
                                    Constant.RESPONSE_FAILURE -> {
                                        showToast(content.errorMsg!!)
                                    }
                                    else -> {
                                        Log.d("onChanged", "onChanged")
                                    }
                                }
                            }
                        }
                        Status.LOADING -> {
                            showLoadingDialog(true)
                        }
                        else -> {
                            showLoadingDialog(false)
                            Log.d("onChanged", "onChanged")
                        }
                    }
                }
            }
        })
    }

}