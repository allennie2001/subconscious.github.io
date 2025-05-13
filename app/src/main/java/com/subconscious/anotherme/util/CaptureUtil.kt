package com.subconscious.anotherme.util

import android.app.Activity
import android.content.Context
import android.media.projection.MediaProjectionManager


object CaptureUtil {
    private fun startScreenCapture(activity: Activity) {
        val mediaProjectionManager =
            activity.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        activity.startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), 123123)
    }
}