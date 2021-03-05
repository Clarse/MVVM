package com.byl.mvvm.widget

import android.view.View
import com.byl.mvvm.widget.ViewClickDelay.SPACE_TIME
import com.byl.mvvm.widget.ViewClickDelay.hash
import com.byl.mvvm.widget.ViewClickDelay.lastClickTime

object ViewClickDelay {
    var hash: Int = 0
    var lastClickTime: Long = 0
    var SPACE_TIME: Long = 1000
}

//Kotlin允许在不使用括号和点号的情况下调用函数，那么这种函数被称为 infix函数。
infix fun View.clicks(clickAction: () -> Unit) {
    this.setOnClickListener {
        if (this.hashCode() != hash) {
            hash = this.hashCode()
            lastClickTime = System.currentTimeMillis()
            clickAction()
        } else {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > SPACE_TIME) {
                lastClickTime = System.currentTimeMillis()
                clickAction()
            }
        }
    }
}
