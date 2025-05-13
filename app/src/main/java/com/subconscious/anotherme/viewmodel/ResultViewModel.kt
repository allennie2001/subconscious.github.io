package com.subconscious.anotherme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {
    private val _selectedApps = MutableLiveData<MutableList<String>>()
    val selectedApps: LiveData<MutableList<String>> = _selectedApps

    fun setSelectedApps(apps: MutableList<String>) {
        _selectedApps.value = apps
    }
} 