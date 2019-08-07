package com.example.mvvmproject.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.mvvmproject.base.activity.BaseActivity
import com.example.mvvmproject.utils.LogUtils
import com.example.mvvmproject.utils.showToast

abstract class BaseFragment : Fragment() {
    val TAG = javaClass.simpleName
    private var mRootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = initRootView(inflater, container, savedInstanceState)
        }
        initView(mRootView!!)
        initData()
        return mRootView
    }


    override fun onAttach(context: Context) {
        LogUtils.dTag(TAG, "onAttach")
        super.onAttach(context)
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.dTag(TAG, "onCreate")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.dTag(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.dTag(TAG, "onDestroy")
    }


    protected abstract fun initRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    //初始化视图
    protected abstract fun initView(view: View)

    //初始化数据
    protected abstract fun initData()

    protected fun showLoadingDialog(visiablity: Boolean) {
        if (activity != null && activity is BaseActivity) {
            val baseActivity = activity as BaseActivity
            baseActivity.showLoadingDialog(visiablity)
        }
    }

    protected fun showToast(content: String) {
        if (activity != null) {
            activity!!.showToast(content)
        }
    }

    protected fun showToast(resId: Int) {
        if (activity != null) {
            activity!!.showToast(resId)
        }
    }
}