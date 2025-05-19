package com.subconscious.anotherme.util

import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo

object TraversalUtil {

    fun findUserNameFromSearchResultFeeds(
        recyclerView: AccessibilityNodeInfo,
        userList: MutableList<String>
    ) {
        if (recyclerView.childCount > 0) {
            for (i in 0 until recyclerView.childCount) {
                val child = recyclerView.getChild(i)
                val userName = findUserNameFromFeedItem(child)
                if (userName.isNotEmpty()) {
                    userList.add(userName)
                }
            }
        }
    }

    private fun findUserNameFromFeedItem(node: AccessibilityNodeInfo?): String {
        val dateNode = findDateByDeepTraversal(node)
        if (dateNode?.parent != null) {
            val userNode = dateNode.parent!!.getChild(0)
            Log.d("zunyu", "userName = ${userNode.text}")
            return userNode.text.toString()
        }
        return ""
    }

    private fun findDateByDeepTraversal(node: AccessibilityNodeInfo?): AccessibilityNodeInfo? {
        var result: AccessibilityNodeInfo? = null
        if (node == null) {
            return null
        }
        if ("android.widget.TextView".equals(node.className)) {
            Log.d("zunyu", "text = " + node.text)
        }
        if (node.childCount > 0) {
            for (i in 0 until node.childCount) {
                findDateByDeepTraversal(node.getChild(i))
            }
//            for (i in 0 until node.childCount) {
//                val child = node.getChild(i)
//                var isNum = false
//                if (child != null && "android.widget.TextView".equals(child.className)) {
//                    Log.d("zunyu, "text = " + child.text)
//                    if (child.text.contains("-")) {
//                        isNum = true
//                        val stringList = child.text.toString().split("-")
//                        for (s in stringList) {
//                            if (s.toIntOrNull() == null) {
//                                isNum = false
//                                break
//                            }
//                        }
//
//                    }
//                }
//                if (isNum) {
//                    return child
//                } else {
//                    if (child != null) {
//                        result = findDateByDeepTraversal(child)
//                        if (result != null) {
//                            return result
//                        }
//                    }
//                }
//            }
        }
        return result
    }

    fun findNodeListByDeepTraversal(
        node: AccessibilityNodeInfo?,
        className: String,
        nodeList: MutableList<AccessibilityNodeInfo>
    ) {
        if (node != null && node.childCount > 0) {
            for (i in 0 until node.childCount) {
                val child = node.getChild(i)
                if (child != null && className == child.className) {
                    nodeList.add(child)
                }
                findNodeListByDeepTraversal(child, className, nodeList)
            }
        }
    }

    fun findNodeByDeepTraversal(
        node: AccessibilityNodeInfo?,
        className: String
    ): AccessibilityNodeInfo? {
        var result: AccessibilityNodeInfo? = null
        if (node != null && node.childCount > 0) {
            for (i in 0 until node.childCount) {
                var child = node.getChild(i)
                Log.d("zunyu", "className = " + child.className)
                if (child != null && className == child.className) {
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