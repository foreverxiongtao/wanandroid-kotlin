package com.example.mvvmproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmproject.R
import com.example.mvvmproject.base.fragment.BaseFragment
import com.example.mvvmproject.base.fragment.BaseLazyFragment

/**
 *    author : desperado
 *    e-mail : foreverxiongtao@sina.com
 *    date   : 2019-08-06 17:04
 *    desc   :首页
 *    version: 1.0
 */
class HomeFragment : BaseLazyFragment() {
    override fun initView(rootView: View, savedInstanceState: Bundle?) {

    }

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun loadDataStart() {
    }


    companion object {
        fun newInstance(bundle: Bundle?): HomeFragment {
            val homeFragment = HomeFragment()
            homeFragment.arguments = bundle
            return homeFragment
        }

    }

}