package com.example.mvvmproject.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.annotation.NonNull
import java.lang.ref.WeakReference
import java.util.*

class AppUtils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var sApplication: Application? = null
        val KEY_REFRESH_NOTIFICATION_COUNT = "KEY_REFRESH_NOTIFICATION_COUNT"
        internal var sTopActivityWeakRef: WeakReference<Activity>? = null
        internal var sActivityList: MutableList<Activity> = LinkedList()
        private var count: Int = 0
        private val mCallbacks = object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                LogUtils.dTag("--Activity--", "onActivityCreated == " + activity.javaClass.name)
                sActivityList.add(activity)
                setTopActivityWeakRef(activity)
            }

            override fun onActivityStarted(activity: Activity) {
                LogUtils.dTag("--Activity--", "onActivityStarted == " + activity.javaClass.name)
                setTopActivityWeakRef(activity)
                if (count == 0) {
                    LogUtils.dTag("LifecycleCallbacks", ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle")
                }
                count++
            }

            override fun onActivityResumed(activity: Activity) {
                LogUtils.dTag("--Activity--", "onActivityResumed == " + activity.javaClass.name)
                setTopActivityWeakRef(activity)
            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {
                count--
                if (count == 0) {
                    LogUtils.dTag("LifecycleCallbacks", ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle")
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                LogUtils.dTag("--Activity--", "onActivityDestroyed == " + activity.javaClass.name)
                sActivityList.remove(activity)
            }
        }

        /**
         * 初始化工具类
         *
         * @param app 应用
         */
        fun init(@NonNull app: Application) {
            AppUtils.sApplication = app
            app.registerActivityLifecycleCallbacks(mCallbacks)
        }

        /**
         * 获取 Application
         *
         * @return Application
         */
        val app: Application
            get() {
                if (sApplication != null) return sApplication as Application
                throw NullPointerException("u should init first")
            }

        private fun setTopActivityWeakRef(activity: Activity) {
            if (sTopActivityWeakRef == null || activity != sTopActivityWeakRef!!.get()) {
                sTopActivityWeakRef = WeakReference(activity)
            }
        }
    }
}