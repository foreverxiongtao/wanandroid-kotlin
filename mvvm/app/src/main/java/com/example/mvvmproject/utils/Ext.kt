package com.example.mvvmproject.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.mvvmproject.constant.Constant


fun Context.showToast(content: String) {
    Constant.showToast?.apply {
        setText(content)
        show()
    } ?: run {
        Toast.makeText(this.applicationContext, content, Toast.LENGTH_SHORT).apply {
            Constant.showToast = this
        }.show()
    }
}

fun Context.showToast(resId: Int) {
    showToast(resources.getString(resId))
}

fun Context.dip2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun <T> Activity.startActivityForClass(activity: Class<T>) {
    val intent = Intent(this, activity)
    startActivity(intent)
}

fun <T> Activity.startActivityForClass(activity: Class<T>, bundle: Bundle) {
    val intent = Intent(this, activity)
    intent.putExtras(bundle)
    startActivity(intent)
}