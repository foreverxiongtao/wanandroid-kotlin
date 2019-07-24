package com.example.mvvmproject.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.view.View
import com.example.mvvmproject.R
import com.example.mvvmproject.base.activity.BaseActivity
import com.example.mvvmproject.constant.Constant
import com.example.mvvmproject.model.entity.LoginResponse
import com.example.mvvmproject.model.entity.Status
import com.example.mvvmproject.utils.StatusBarUtil
import com.example.mvvmproject.utils.showToast
import com.example.mvvmproject.utils.statusbar.Eyes
import com.example.mvvmproject.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

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
            }
        })
    }
    override fun initData() {
        initTitleBar(toolbar,"")
//        Eyes.setStatusBarLightMode(this, Color.WHITE)
//        Eyes.setStatusBarLightMode(this, resources.getColor(R.color.white))

//        mViewModel.addObserver(this, object : Observer<LoginResponse> {
//            override fun onChanged(t: LoginResponse?) {
//                t?.let {
//                    it.status?.let { status ->
//                        if (status == Status.Error) {
//                            showToast(R.string.str_net_error)
//                        }
//                    } ?: kotlin.run {
//                        it.errorCode?.let { errorCode ->
//                            if (errorCode == Constant.RESPONSE_FAILURE) {
//                                showToast(it.errorMsg!!)
//                            } else if (errorCode == Constant.RESPONSE_SUCCESS) {
//                                showToast(getString(R.string.str_register_success))
//                            }
//
//                        } ?: run {
//                            showToast(getString(R.string.str_register_failured))
//                        }
//                    }
//                } ?: run {
//                    showToast(getString(R.string.str_register_failured))
//                }
//            }
//        })
    }

}