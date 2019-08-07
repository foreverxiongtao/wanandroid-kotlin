package com.example.mvvmproject.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.mvvmproject.utils.LogUtils

/**
 *    author : desperado
 *    e-mail : foreverxiongtao@sina.com
 *    date   : 2019-08-06 17:49
 *    desc   :  延迟加载fragment基类
 *    version: 1.0
 */
abstract class BaseLazyFragment : Fragment() {
    private val TAG = BaseLazyFragment::class.java.simpleName
    private var mHaveLoadData: Boolean = false
    private var mRootView: View? = null
    // 表示找控件完成, 给控件们设置数据不会报空指针了
    private var mViewInflateFinished: Boolean = false

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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtils.dTag(TAG, "onCreateView")
        if (mRootView != null) {
            return mRootView
        }
        mRootView = inflater.inflate(getRootLayoutId(), null)
        initView(mRootView!!, savedInstanceState)
        mViewInflateFinished = true
        return mRootView
    }

    protected abstract fun initView(
        rootView: View,
        savedInstanceState: Bundle?
    )

    protected abstract fun getRootLayoutId(): Int


    // 表示开始加载数据, 但不表示数据加载已经完成
    protected abstract fun loadDataStart()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        LogUtils.dTag(TAG, "setUserVisibleHint----" + isVisibleToUser)
        super.setUserVisibleHint(isVisibleToUser)
        // 如果还没有加载过数据 && 用户切换到了这个fragment
        if (!mHaveLoadData && isVisibleToUser) {
            loadDataStart()
            mHaveLoadData = true
        }
    }
}