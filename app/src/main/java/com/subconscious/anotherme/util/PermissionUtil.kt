package com.subconscious.anotherme.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


/**
 * @author zunyu
 * @version 2021/11/6
 */
object PermissionUtil {

    fun checkReadPhoneStatePermission(activity: Activity):Boolean {
        return if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.READ_PHONE_STATE), 123)
            false
        } else {
            true
        }
    }
}