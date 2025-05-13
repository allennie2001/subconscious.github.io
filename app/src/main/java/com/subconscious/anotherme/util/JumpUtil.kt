package com.subconscious.anotherme.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.widget.Toast
import com.subconscious.anotherme.activity.ResultActivity
import java.lang.ref.WeakReference

object JumpUtil {

    var activity: WeakReference<Activity>? = null

    fun init(activity: Activity) {
        this.activity = WeakReference(activity)
    }

    fun jumpMyselfActivity() {
        val intent = Intent(activity?.get(),ResultActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        activity!!.get()!!.startActivity(intent)
    }

    fun jumpXiaoHongShuApp() {
        val packageManager: PackageManager = activity?.get()!!.packageManager
        if (checkPackInfo(activity?.get()!!, "com.xingin.xhs")) {
            val intent = packageManager.getLaunchIntentForPackage("com.xingin.xhs")
            intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity?.get()?.startActivity(intent)
        } else {
            Toast.makeText(activity?.get(), "没有安装小红书", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private fun checkPackInfo(context: Context, packageName: String): Boolean {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = context.packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return packageInfo != null
    }
}