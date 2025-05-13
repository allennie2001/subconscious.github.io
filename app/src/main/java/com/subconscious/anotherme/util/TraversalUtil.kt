package com.subconscious.anotherme.util

import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo

object TraversalUtil {
    fun findNodeByDeepTraversal(
        node: AccessibilityNodeInfo,
        className: String
    ): AccessibilityNodeInfo? {
        var result: AccessibilityNodeInfo? = null
        if (node.childCount > 0) {
            for (i in 0 until node.childCount) {
                var child = node.getChild(i)
                Log.d("findNodeByDeepTraversal", "className = " + child.className)
                if (className.equals(child.className)) {
                    result = child
                    break
                } else {
                    result = findNodeByDeepTraversal(child, className)
                }
            }
        }
        return result
    }

    fun logTextInChildren(node: AccessibilityNodeInfo?) {
        if (node != null) {
            if (!node.text.isNullOrEmpty()) {
                Log.d("zunyu", node.text.toString())
            }
            if (node.childCount > 0) {
                for (i in 0 until node.childCount) {
                    val child = node.getChild(i)
                    logTextInChildren(child)
                }
            }
        }
    }
}