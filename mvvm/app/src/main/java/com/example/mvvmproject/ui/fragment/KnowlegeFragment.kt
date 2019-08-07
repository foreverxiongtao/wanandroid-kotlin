package com.example.mvvmproject.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.mvvmproject.R
import com.example.mvvmproject.base.fragment.BaseLazyFragment

class KnowlegeFragment : BaseLazyFragment() {
    override fun getRootLayoutId(): Int {
        return R.layout.fragment_knowledge
    }

    override fun initView(rootView: View, savedInstanceState: Bundle?) {

    }

    override fun loadDataStart() {

    }

    companion object {
        fun newInstance(bundle: Bundle?): KnowlegeFragment {
            val fragment = KnowlegeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}