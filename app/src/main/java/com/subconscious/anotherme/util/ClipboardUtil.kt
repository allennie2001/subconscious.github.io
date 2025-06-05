package com.subconscious.anotherme.util

import android.accessibilityservice.AccessibilityService
import android.app.ActivityManager
import android.content.ActivityNotFoundException
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import java.io.IOException
import java.lang.ref.WeakReference


object ClipboardUtil {
    var context: WeakReference<Context>? = null
    val handler: Handler = Handler(Looper.getMainLooper())

    fun init(context: Context) {
        this.context = WeakReference(context)
        val clipboard = ClipboardUtil.context?.get()
            ?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.let { clipboardManager ->
            clipboardManager.addPrimaryClipChangedListener {
                Log.d("zunyu", "onPrimaryClipChanged ()")
                if (clipboardManager.hasPrimaryClip()) {
                    val clip = clipboardManager.primaryClip
                    clip?.let {
                        val text = clip.getItemAt(0).text
                        Log.d("zunyu", "剪贴板内容: $text")
                    }
                }
            }
        }
    }

    fun initListener(context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.let { clipboardManager ->
            clipboardManager.addPrimaryClipChangedListener {
                Log.d("zunyu", "onPrimaryClipChanged ()")
                if (clipboardManager.hasPrimaryClip()) {
                    val clip = clipboardManager.primaryClip
                    clip?.let {
                        val text = clip.getItemAt(0).text
                        Log.d("zunyu", "剪贴板内容: $text")
                    }
                }
            }
        }
    }

    var profileIdList: MutableMap<String, String> = mutableMapOf()

    fun getClipboardContent(context: Context, curUser: String?) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboard.hasPrimaryClip()) {
            val clip = clipboard.primaryClip
            clip?.let {
                val text = clip.getItemAt(0).text
//                Log.d("zunyu", "$text")
                var split = text.toString().split("?")
                var path = split[0].split("/")
                val profileId = path.get(path.size - 1)
                Log.d("zunyu", "$curUser $profileId")
                profileIdList.put(curUser ?: "null", profileId)
            }
        } else {
            Log.d("zunyu", "剪贴板没权限")
        }
    }

    fun bringAppToForeground(packageName: String) {
        try {
            // 获取目标应用的主 Activity Intent

            val intent = context?.get()?.packageManager?.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                intent.setAction(Intent.ACTION_MAIN)
                context?.get()?.startActivity(intent)
            } else {
                // 处理未安装或无法启动的情况
            }
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }


    }

    //当本应用位于后台时，则将它切换到最前端
    fun setTopApp(packageName: String) {
        //获取ActivityManager
        val activityManager = context?.get()?.getSystemService(ACTIVITY_SERVICE) as ActivityManager

        //获得当前运行的task(任务)
        val taskInfoList = activityManager.getRunningTasks(100)
        for (taskInfo in taskInfoList) {
            //找到本应用的 task，并将它切换到前台
            if (taskInfo.baseActivity!!.packageName == packageName) {
                activityManager.moveTaskToFront(taskInfo.id, 0)
                break
            }
        }
    }

    fun getUserProfileId(
        service: AccessibilityService,
        curSearchContent: String,
        listener: Listener
    ) {
        ClickUtil.performActionClickByText("更多", service)
        handler.postDelayed({
            ClickUtil.boundClick(100, ScreenUtil.screenHeight!! - 100, service)
            handler.postDelayed({
                ClipboardUtil.setTopApp("com.subconscious.anotherme")
                handler.postDelayed({
                    ClipboardUtil.getClipboardContent(service, curSearchContent)
                    ClipboardUtil.bringAppToForeground("com.xingin.xhs")
                    listener.onSuccess()
                }, 500)
            }, 1000)
        }, 1000)
    }

    fun searchChildrenProfile(
        service: AccessibilityService,
        parent: AccessibilityNodeInfo,
        index: Int,
        curSearchContent: String,
        listener: Listener
    ) {
        if (index < parent.childCount) {
            val child = parent.getChild(index)
            ClickUtil.pointClick(child, service)
            getUserProfileId(service, curSearchContent, object : Listener {
                override fun onSuccess() {
                    ClickUtil.performActionClickByText("返回", service)
                    searchChildrenProfile(service, parent, 1 + index, curSearchContent, listener)
                }

                override fun onFailure() {
                    listener.onFinish()
                }

                override fun onFinish() {
                    listener.onFinish()
                }

            })
        } else {
            listener.onFinish()
        }
    }

    interface Listener {
        fun onSuccess()

        fun onFailure()

        fun onFinish()
    }

}