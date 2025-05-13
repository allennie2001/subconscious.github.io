package com.subconscious.anotherme.adapter.viewmodel

import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // 私有变量
    private val _selectedPositions = MutableLiveData<Set<Int>>(emptySet())
    private val _navigateToResult = MutableLiveData<List<String>>()
    private val _apps = MutableLiveData<List<String>>()

    // 公开的 LiveData
    val selectedPositions: LiveData<Set<Int>> = _selectedPositions
    val navigateToResult: LiveData<List<String>> = _navigateToResult
    val apps: LiveData<List<String>> = _apps

    /**
     * 加载已安装的应用列表
     */
    fun loadInstalledApps(context: Context) {
        val packageManager = context.packageManager
        val installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { appInfo ->
                // 过滤掉系统应用
                (appInfo.flags and android.content.pm.ApplicationInfo.FLAG_SYSTEM) == 0
            }
            .map { appInfo ->
                packageManager.getApplicationLabel(appInfo).toString()
            }
            .sorted()
        _apps.value = installedApps
    }

    /**
     * 处理应用选择事件
     */
    fun onAppSelected(position: Int) {
        val currentSelected = _selectedPositions.value?.toMutableSet() ?: mutableSetOf()
        if (currentSelected.contains(position)) {
            currentSelected.remove(position)
        } else {
            currentSelected.add(position)
        }
        _selectedPositions.value = currentSelected
    }

    /**
     * 处理全选/取消全选
     */
    fun toggleSelectAll() {
        val currentApps = _apps.value ?: emptyList()
        val currentSelected = _selectedPositions.value ?: emptySet()
        
        if (currentSelected.size == currentApps.size) {
            // 如果已经全选，则取消全选
            _selectedPositions.value = emptySet()
        } else {
            // 否则全选
            _selectedPositions.value = currentApps.indices.toSet()
        }
    }

    /**
     * 检查指定位置的应用是否被选中
     */
    fun isSelected(position: Int): Boolean {
        return _selectedPositions.value?.contains(position) ?: false
    }

    /**
     * 获取选中的应用列表
     */
    private fun getSelectedApps(): List<String> {
        val currentApps = _apps.value ?: emptyList()
        return _selectedPositions.value?.map { currentApps[it] } ?: emptyList()
    }

    /**
     * 处理确认按钮点击事件
     */
    fun onConfirmClick() {
        _navigateToResult.value = getSelectedApps()
    }
} 