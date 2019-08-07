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
import com.example.mvvmproject.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class RegisterActivity : BaseActivity() {

    private val mViewModel: UserViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun initParams(intent: Intent) {
    }

    override fun bindView(): View {
        return mLayoutInflator.inflate(R.layout.activity_register, null, false)
    }

    override fun initView(rootView: View) {
        btn_register_save.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val nickName = et_register_nick.text.toString()
                if (nickName.isEmpty()) {
                    showToast(getString(R.string.str_please_input_nick))
                    return
                }
                val pwd = et_register_pwd.text.toString()
                if (pwd.isEmpty()) {
                    showToast(getString(R.string.str_input_pwd))
                    return
                }

                val pwdAgain = et_register_pwd_again.text.toString()
                if (pwdAgain.isEmpty()) {
                    showToast(getString(R.string.str_input_pwd_again))
                    return
                }
                mViewModel.register(nickName, pwd, pwdAgain)
                    .observe(this@RegisterActivity, object : Observer<Resource<Result.Data<LoginResponse>>> {
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
                                                    showToast(R.string.str_register_success)
                                                    Log.d("onChanged", "onChanged")
                                                    finish()
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
        })
    }

    override fun initData() {
        initTitleBar(toolbar, resources.getString(R.string.str_register_save))
    }

}