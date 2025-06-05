package com.subconscious.anotherme.service

//noinspection SuspiciousImport
import android.accessibilityservice.AccessibilityService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import com.subconscious.anotherme.R
import com.subconscious.anotherme.controller.BaseController
import com.subconscious.anotherme.controller.XHSSearchUserFollowListController
import com.subconscious.anotherme.controller.XiaoHongShuAutoReplyController
import com.subconscious.anotherme.controller.XiaoHongShuSearchContentController
import com.subconscious.anotherme.controller.XiaoHongShuSearchGoodsController
import com.subconscious.anotherme.controller.XiaoHongShuSearchUserController
import com.subconscious.anotherme.util.ClipboardUtil


class AutoService : AccessibilityService() {
    var controller: BaseController? = null

    override fun onCreate() {
        super.onCreate()
        Log.d("zunyu", "DouyinAutoToolService---->onCreate被调用，启动前台service")
        startForService()
    }

    fun startForService() {
        val CHANNEL_ONE_ID = "CHANNEL_ONE_ID"
        val CHANNEL_ONE_NAME = "CHANNEL_ONE_ID"
        //进行8.0的判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(
                CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = getColor(R.color.teal_200)
            notificationChannel.setShowBadge(true)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }

        //如果API大于18，需要弹出一个可见通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val builder = Notification.Builder(this)
            builder.setSmallIcon(R.mipmap.ic_launcher)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setChannelId(CHANNEL_ONE_ID)
            }
            builder.setContentTitle("KeepAppAlive")
            builder.setContentText("DouyinAutoToolService is runing...")
            startForeground(100, builder.build())
            // 如果觉得常驻通知栏体验不好
            // 可以通过启动CancelNoticeService，将通知移除，oom_adj值不变
//            val intent = Intent(this, CancelNoticeService::class.java)
//            startService(intent)
        } else {
            startForeground(100, Notification())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 如果Service被杀死，干掉通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val mManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            mManager.cancel(100)
        }
        Log.d("zunyu", "DouyinAutoToolService---->onDestroy，前台service被杀死")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i("zunyu", "----key_log----onServiceConnected------------")
        ClipboardUtil.initListener(this)
        initControllers()
        Toast.makeText(this, "权限开启成功，请跳转小红书", Toast.LENGTH_SHORT).show()
        ClipboardUtil.bringAppToForeground("com.xingin.xhs")
    }

    private fun initControllers() {
        controller = XiaoHongShuSearchUserController(this, "com.xingin.xhs")
//        controller = XiaoHongShuSearchContentController(this, "com.xingin.xhs")
//        controller = XiaoHongShuAutoReplyController(this, "com.xingin.xhs")
//        controller = XiaoHongShuSearchGoodsController(this, "com.xingin.xhs")
//        controller = XHSSearchUserFollowListController(this, "com.xingin.xhs")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // 均在主线程
//        Log.i(
//            "zunyu",
//            "----event---pkg = ${event?.packageName}----event---page = ${event?.className}---type = ${event?.eventType}----"
//        )
        controller?.onAccessibilityEvent(event)
    }

    override fun onInterrupt() {
        Log.i("zunyu", "-----------onInterrupt------------")
    }
}