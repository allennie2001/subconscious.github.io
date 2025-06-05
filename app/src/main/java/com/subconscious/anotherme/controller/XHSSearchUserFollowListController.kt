package com.subconscious.anotherme.controller

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import com.subconscious.anotherme.util.ClickUtil
import com.subconscious.anotherme.util.ClipboardUtil
import com.subconscious.anotherme.util.ScreenUtil
import com.subconscious.anotherme.util.TraversalUtil


class XHSSearchUserFollowListController(var service: AccessibilityService, var curPackage: String) :
    BaseController() {

    var curPage: String? = null
    var curSearchContent: String? = null
    var searchDataList: MutableList<String> = mutableListOf()

    var isBackFromTalentPage: Boolean = false

    val hander: Handler = Handler(Looper.getMainLooper())
    val usersFromFollows: MutableList<String> = mutableListOf()

    init {
//        searchDataList.add("时尚薯")
        searchDataList.add("潮流情报官")
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        super.onAccessibilityEvent(event)
        when (event!!.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                // 获取当前的包名和类名
                val packageName = event.packageName.toString()
                val className = event.className.toString()


                // 判断是否是新的页面
                if (curPackage.equals(packageName) || !curPage.equals(className)
                ) {
                    // 页面发生变化，可以认为是跳转
                    Log.d("zunyu", "New Page: $className")
                    // 更新当前页面信息
                    curPage = className
                    checkPage(event)
                }
            }

            AccessibilityEvent.TYPE_VIEW_FOCUSED -> {
                if (event.source != null && searchDataList.size > 0) {
                    val bundle = Bundle()
                    bundle.putCharSequence(
                        AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                        searchDataList[0]
                    )
                    event.source!!.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, bundle)
                }

                if (isBackFromTalentPage) {
                    ClickUtil.performActionClickByText("全部删除", service)
                }
            }

            AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> {
                if (event.source != null) {
                    val text = event.source!!.text.toString()
                    if (text.startsWith("搜索, ")) {
                        curSearchContent = text.removeRange(0, 4)
                    } else {
                        curSearchContent = text
                    }
                }
                val searchListNode = TraversalUtil.findNodeByDeepTraversal(
                    service.rootInActiveWindow,
                    "androidx.recyclerview.widget.RecyclerView"
                )
                var baseSearchLog: String? = null
                if (curPage.equals("com.xingin.alioth.search.GlobalSearchActivity")) {
                    baseSearchLog = "默认搜索推荐"
                } else {
                    baseSearchLog = "搜索{${searchDataList.get(0)}提示词}"
                }
//                Log.d("zunyu", "-----------${baseSearchLog}：爬虫开始-------------")
//                searchListNode?.let { TraversalUtil.logTextInChildren(it) }
//                Log.d("zunyu", "-----------${baseSearchLog}：爬虫结束-------------")


                if (isBackFromTalentPage) {
                    isBackFromTalentPage = false
                } else {
                    ClickUtil.performActionClickByText("搜索", service)
                }
            }

            AccessibilityEvent.TYPE_VIEW_SCROLLED -> {
                if (isBackFromTalentPage) {
                    ClickUtil.performActionClickByText("全部删除", service)
                    return
                }
                val avatarLayoutList =
                    service.rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.xingin.xhs:id/avatarLayout")
                if (avatarLayoutList != null && avatarLayoutList.size > 0) {
                    val avatar = avatarLayoutList[0]
                    if (avatar.isClickable) {
                        ClickUtil.pointClick(avatar, service)
                    }
                }

                if ("ky9.a".equals(curPage) || "com.xingin.matrix.v2.profile.relationmerge.RelationMergeActivity".equals(
                        curPage
                    )
                ) {
                    hander.postDelayed({
                        var nodeList: MutableList<AccessibilityNodeInfo> = mutableListOf()
                        TraversalUtil.findNodeListByDeepTraversal(
                            service.rootInActiveWindow,
                            "androidx.recyclerview.widget.RecyclerView",
                            nodeList
                        )
                        if (nodeList.size > 1) {
                            val recyclerView = nodeList[1]
                            TraversalUtil.logTextInChildrenNextFollow(recyclerView)
                            recyclerView.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
                            Thread.sleep(1000)
                        }
                    }, 1000)
                }
            }
        }
    }


    private fun checkPage(event: AccessibilityEvent?) {
        when (curPage) {
            // 首页
            "com.xingin.xhs.index.v2.IndexActivityV2" -> {
                ClickUtil.performActionClickByText("搜索", service)
            }

            "com.xingin.alioth.search.GlobalSearchActivity" -> {

            }

            "com.xingin.matrix.v2.profile.newpage.NewOtherUserActivity" -> {
                ClickUtil.performActionClickByText("关注", service)
                Thread.sleep(1000)
            }

            "ky9.a" -> {
                hander.postDelayed({
                    var nodeList: MutableList<AccessibilityNodeInfo> = mutableListOf()
                    TraversalUtil.findNodeListByDeepTraversal(
                        service.rootInActiveWindow,
                        "androidx.recyclerview.widget.RecyclerView",
                        nodeList
                    )
                    if (nodeList.size > 1) {
                        val recyclerView = nodeList[1]
                        TraversalUtil.logTextInChildrenNextFollow(recyclerView)
                        recyclerView.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
                        Thread.sleep(1000)
                    }

                }, 1000)
            }
        }
    }

    private fun returnLastPage() {
        if (searchDataList.size > 0) {
            searchDataList.removeAt(0)
            isBackFromTalentPage = true
            hander.postDelayed(
                { ClickUtil.performActionClickByText("返回", service) },
                500
            )
        }
    }
}