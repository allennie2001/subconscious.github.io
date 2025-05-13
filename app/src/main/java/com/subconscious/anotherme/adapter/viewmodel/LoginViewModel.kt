package com.subconscious.anotherme.adapter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            _errorMessage.value = "请输入用户名和密码"
            return
        }

        // TODO: 实现实际的登录请求
        // 这里暂时模拟登录成功
        _loginResult.value = true
    }
} 