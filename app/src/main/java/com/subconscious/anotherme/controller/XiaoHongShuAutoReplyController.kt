package com.subconscious.anotherme.controller

import android.accessibilityservice.AccessibilityService
import android.graphics.Rect
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
import com.subconscious.anotherme.util.TraversalUtil.logTextInChildren


class XiaoHongShuAutoReplyController(
    var service: AccessibilityService,
    var curPackage: String
) :
    BaseController() {

    var curPage: String? = null
    var curSearchContent: String? = null
    var searchDataList: MutableList<String> = mutableListOf()

    var notesResultSize: Int = 100
    var usersFromNote: MutableList<String> = mutableListOf()

    var isBackFromTalentPage: Boolean = false
    var isDeeping = false

    val hander: Handler = Handler(Looper.getMainLooper())

    init {
        searchDataList.add("老凤祥凤凰戒指");
        searchDataList.add("老凤祥蝴蝶手链");
        searchDataList.add("老凤祥爱豆金条");
        searchDataList.add("老凤祥福袋");
        searchDataList.add("老凤祥金豆豆");
        searchDataList.add("六福珠宝葫芦吊坠");
        searchDataList.add("六福珠宝霞冠凤羽");
        searchDataList.add("六福珠宝铂金鱼骨素链");
        searchDataList.add("六福珠宝碧婉和田玉");
        searchDataList.add("六福珠宝珍珠耳夹");
        searchDataList.add("周生生酷黑转运珠");
        searchDataList.add("周生生小圆满平安扣");
        searchDataList.add("周生生药师佛");
        searchDataList.add("周生生莫比乌斯环戒指");
        searchDataList.add("周生生炫幻四叶草项链");
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
                var baseSearchLog: String? = null
                val searchListNode = TraversalUtil.findNodeByDeepTraversal(
                    service.rootInActiveWindow,
                    "androidx.recyclerview.widget.RecyclerView"
                )
                if (curPage.equals("com.xingin.alioth.search.GlobalSearchActivity")) {
                    baseSearchLog = "默认搜索推荐"
                } else {
                    baseSearchLog = "搜索{${searchDataList[0]}提示词}"
                }
                if (searchListNode != null && searchListNode.childCount > 0) {
                    for (i in 0 until searchListNode.childCount) {
                        val child = searchListNode.getChild(i)
                        var rect = Rect()
                        child.getBoundsInScreen(rect)
                        if (rect.right < ScreenUtil.screenWidth!! || rect.left > 0) {
                            // 命中左右feeds流卡片
//                            ClickUtil.pointClick(child, service)
                            TraversalUtil.logTextInChildren(child)
                        }
                    }
                }
                searchListNode?.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
                Thread.sleep(1000)


                if (isBackFromTalentPage) {
                    isBackFromTalentPage = false
                } else {
                    ClickUtil.performActionClickByText("搜索", service)
                }
            }

            AccessibilityEvent.TYPE_VIEW_SCROLLED -> {
                if (!isDeeping) {
                    isDeeping = true
                    hander.postDelayed({
                        if (usersFromNote.size <= 50) {
                            var nodeList: MutableList<AccessibilityNodeInfo> = mutableListOf()
                            TraversalUtil.findNodeListByDeepTraversal(
                                service.rootInActiveWindow,
                                "androidx.recyclerview.widget.RecyclerView",
                                nodeList
                            )
                            for (nodeInfo in nodeList) {
                                TraversalUtil.logTextInChildren(nodeInfo)
//                                TraversalUtil.findUserNameFromSearchResultFeeds(nodeInfo, usersFromNote)
                            }
                        }
                        isDeeping = false
                    }, 5000)

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

            "com.xingin.matrix.v2.profile.newpage.NewOtherUserActivity" -> {
                Log.d("zunyu", "-----------个人主页：爬虫开始-------------")
                TraversalUtil.logTextInChildren(service.rootInActiveWindow)
                Log.d("zunyu", "-----------个人主页：爬虫结束-------------")

                ClickUtil.performActionClickByText("更多", service)

                hander.postDelayed({
//                    ClickUtil.boundClick(100, ScreenUtil.screenHeight!! - 100, service)
//                    ClipboardUtil.setTopApp("com.subconscious.anotherme")
//                    hander.postDelayed({
//                        ClipboardUtil.getClipboardContent(service)
//                        hander.postDelayed({
//                            searchDataList.removeAt(0)
//                            ClipboardUtil.bringAppToForeground("com.xingin.xhs")
//                        }, 500)
//                                       }, 500)

                    ClickUtil.boundClick(100, ScreenUtil.screenHeight!! - 100, service)
                    ClipboardUtil.setTopApp("com.subconscious.anotherme")
                    hander.postDelayed({
                        ClipboardUtil.getClipboardContent(service, curSearchContent)
                        searchDataList.removeAt(0)
                        ClipboardUtil.bringAppToForeground("com.xingin.xhs")
                    }, 500)
                }, 1000)

            }

            "com.xingin.alioth.search.GlobalSearchActivity" -> {

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