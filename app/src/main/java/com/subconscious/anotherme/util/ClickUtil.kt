package com.subconscious.anotherme.util

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityService.GestureResultCallback
import android.accessibilityservice.GestureDescription
import android.accessibilityservice.GestureDescription.StrokeDescription
import android.graphics.Path
import android.graphics.Rect
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo

object ClickUtil {
    fun performActionClickById(id: String, service: AccessibilityService) {
        var nodeInfos = service.rootInActiveWindow.findAccessibilityNodeInfosByViewId(id)
        if (!nodeInfos.isNullOrEmpty()) {
            var nodeInfo = nodeInfos.get(0)
            if (nodeInfo.isClickable) {
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            }
        }
    }

    fun performActionClickByText(text: String, service: AccessibilityService) {
        var nodeInfos = service.rootInActiveWindow.findAccessibilityNodeInfosByText(text)
        if (!nodeInfos.isNullOrEmpty()) {
            for (info in nodeInfos) {
                if ("搜索".equals(text) && "拍照搜索".equals(info.contentDescription)) {
                    continue
                }
                if (info.isClickable) {
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                }
            }
        }
    }

    fun pointClick(node: AccessibilityNodeInfo, service: AccessibilityService) {

        // 获取节点在屏幕上的位置
        val bounds = Rect()
        node.getBoundsInScreen(bounds)
        boundClick(bounds.centerX(), bounds.centerY(), service)
    }

    fun boundClick(x: Int, y: Int, service: AccessibilityService) {
        // 构造一个点击手势（按下 + 抬起）
        val path: Path = Path()
        path.moveTo(x.toFloat(), y.toFloat())
        val builder = GestureDescription.Builder()
        builder.addStroke(StrokeDescription(path, 0, 50)) // 50ms 内完成点击
        val gesture = builder.build()


        // 执行手势
        service.dispatchGesture(gesture, object : GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription) {
                super.onCompleted(gestureDescription)
                Log.d("zunyu","完成点击")
            }
        }, null)
    }
}