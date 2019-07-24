package com.example.mvvmproject.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvmproject.App

class SPUtils private constructor(spName: String) {
    private var sp: SharedPreferences = App.getApp().getSharedPreferences(spName, Context.MODE_PRIVATE)

    companion object {
        fun getInstance(spName: String): SPUtils {
            return SPUtilsHolder(spName).mSpUtils
        }

        fun getInstance(): SPUtils = getInstance("")
    }

    private class SPUtilsHolder(spName: String) {
        val mSpUtils = SPUtils(spName)
    }

    /**
     * SP 中写入 String
     *
     * @param key      键
     * @param value    值
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * `false`: [SharedPreferences.Editor.apply]
     */
    fun put(
        key: String,
        value: String,
        isCommit: Boolean
    ) {
        if (isCommit) {
            sp.edit().putString(key, value).commit()
        } else {
            sp.edit().putString(key, value).apply()
        }
    }

    /**
     * SP 中写入 String
     *
     * @param key   键
     * @param value 值
     */
    fun put(key: String, value: String) {
        put(key, value, false)
    }


    /**
     * SP 中读取 String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值`""`
     */
    fun getString(key: String): String {
        return getString(key, "")
    }

    /**
     * SP 中读取 String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    fun getString(key: String, defaultValue: String): String {
        return sp.getString(key, defaultValue)
    }

    /**
     * SP 中写入 int
     *
     * @param key   键
     * @param value 值
     */
    fun put(key: String, value: Int) {
        put(key, value, false)
    }

    /**
     * SP 中写入 int
     *
     * @param key      键
     * @param value    值
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * `false`: [SharedPreferences.Editor.apply]
     */
    fun put(key: String, value: Int, isCommit: Boolean) {
        if (isCommit) {
            sp.edit().putInt(key, value).commit()
        } else {
            sp.edit().putInt(key, value).apply()
        }
    }

    /**
     * SP 中读取 int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    fun getInt(key: String): Int {
        return getInt(key, -1)
    }

    /**
     * SP 中读取 int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    fun getInt(key: String, defaultValue: Int): Int {
        return sp.getInt(key, defaultValue)
    }

    /**
     * SP 中写入 long
     *
     * @param key   键
     * @param value 值
     */
    fun put(key: String, value: Long) {
        put(key, value, false)
    }

    /**
     * SP 中写入 long
     *
     * @param key      键
     * @param value    值
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * `false`: [SharedPreferences.Editor.apply]
     */
    fun put(key: String, value: Long, isCommit: Boolean) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit()
        } else {
            sp.edit().putLong(key, value).apply()
        }
    }

    /**
     * SP 中读取 long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    fun getLong(key: String): Long {
        return getLong(key, -1L)
    }

    /**
     * SP 中读取 long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    fun getLong(key: String, defaultValue: Long): Long {
        return sp.getLong(key, defaultValue)
    }

    /**
     * SP 中写入 float
     *
     * @param key   键
     * @param value 值
     */
    fun put(key: String, value: Float) {
        put(key, value, false)
    }

    /**
     * SP 中写入 float
     *
     * @param key      键
     * @param value    值
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * `false`: [SharedPreferences.Editor.apply]
     */
    fun put(key: String, value: Float, isCommit: Boolean) {
        if (isCommit) {
            sp.edit().putFloat(key, value).commit()
        } else {
            sp.edit().putFloat(key, value).apply()
        }
    }

    /**
     * SP 中读取 float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    fun getFloat(key: String): Float {
        return getFloat(key, -1f)
    }

    /**
     * SP 中读取 float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    fun getFloat(key: String, defaultValue: Float): Float {
        return sp.getFloat(key, defaultValue)
    }

    /**
     * SP 中写入 boolean
     *
     * @param key   键
     * @param value 值
     */
    fun put(key: String, value: Boolean) {
        put(key, value, false)
    }

    /**
     * SP 中写入 boolean
     *
     * @param key      键
     * @param value    值
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * `false`: [SharedPreferences.Editor.apply]
     */
    fun put(key: String, value: Boolean, isCommit: Boolean) {
        if (isCommit) {
            sp.edit().putBoolean(key, value).commit()
        } else {
            sp.edit().putBoolean(key, value).apply()
        }
    }

    /**
     * SP 中读取 boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值`false`
     */
    fun getBoolean(key: String): Boolean {
        return getBoolean(key, false)
    }

    /**
     * SP 中读取 boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sp.getBoolean(key, defaultValue)
    }

    /**
     * SP 中写入 String 集合
     *
     * @param key    键
     * @param values 值
     */
    fun put(key: String, values: Set<String>) {
        put(key, values, false)
    }

    /**
     * SP 中写入 String 集合
     *
     * @param key      键
     * @param values   值
     * @param isCommit `true`: [SharedPreferences.Editor.commit]<br></br>
     * `false`: [SharedPreferences.Editor.apply]
     */
    fun put(
        key: String,
        values: Set<String>,
        isCommit: Boolean
    ) {
        if (isCommit) {
            sp.edit().putStringSet(key, values).commit()
        } else {
            sp.edit().putStringSet(key, values).apply()
        }
    }

}