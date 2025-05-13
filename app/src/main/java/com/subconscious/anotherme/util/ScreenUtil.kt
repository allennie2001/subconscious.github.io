package com.subconscious.anotherme.util

import android.content.Context
import android.view.WindowManager

object ScreenUtil {
    var screenWidth: Int? = null
    var screenHeight: Int? = null

    fun init(context: Context) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        screenHeight = wm.defaultDisplay.height
        screenWidth = wm.defaultDisplay.width
    }
}