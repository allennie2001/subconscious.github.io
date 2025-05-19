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


class XiaoHongShuSearchContentController(
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
        searchDataList.add("男明星穿搭");
        searchDataList.add("明星同款穿搭");
        searchDataList.add("明星私服");
        searchDataList.add("女明星穿搭");
        searchDataList.add("明星同款鞋子");
        searchDataList.add("明星夏季穿搭");
        searchDataList.add("虞书欣同款穿搭");
        searchDataList.add("赵露思同款穿搭");
        searchDataList.add("欧阳娜娜同款穿搭");
        searchDataList.add("钟楚曦同款穿搭");
        searchDataList.add("金靖同款穿搭");
        searchDataList.add("赵昭仪同款穿搭");
        searchDataList.add("宋妍霏同款穿搭");
        searchDataList.add("jennie同款穿搭");
        searchDataList.add("徐若晗同款穿搭");
        searchDataList.add("李兰迪同款穿搭");
        searchDataList.add("赵今麦同款穿搭");
        searchDataList.add("卢昱晓同款穿搭");
        searchDataList.add("张婧仪同款穿搭");
        searchDataList.add("虞书欣同款");
        searchDataList.add("赵露思同款");
        searchDataList.add("欧阳娜娜同款");
        searchDataList.add("钟楚曦同款");
        searchDataList.add("金靖同款");
        searchDataList.add("赵昭仪同款");
        searchDataList.add("宋妍霏同款");
        searchDataList.add("jennie同款");
        searchDataList.add("徐若晗同款");
        searchDataList.add("李兰迪同款");
        searchDataList.add("赵今麦同款");
        searchDataList.add("卢昱晓同款");
        searchDataList.add("张婧仪同款");
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
                    baseSearchLog = "搜索{${searchDataList.get(0)}提示词}"
                }
                Log.d("zunyu", "-----------${baseSearchLog}：爬虫开始-------------")
                searchListNode?.let { TraversalUtil.logTextInChildren(it) }
                Log.d("zunyu", "-----------${baseSearchLog}：爬虫结束-------------")


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
                                TraversalUtil.findUserNameFromSearchResultFeeds(nodeInfo, usersFromNote)
                            }
                        }
                        isDeeping = false
                    },5000)

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
                        ClipboardUtil.getClipboardContent(service)
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