package com.subconscious.anotherme.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.subconscious.anotherme.adapter.AppAdapter
import com.subconscious.anotherme.adapter.viewmodel.MainViewModel
import com.subconscious.anotherme.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: AppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 初始化 ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // 设置 RecyclerView
        adapter = AppAdapter(viewModel)
        binding.rvApps.layoutManager = LinearLayoutManager(this)
        binding.rvApps.adapter = adapter

        // 加载应用列表
        viewModel.loadInstalledApps(this)

        // 设置全选按钮点击事件
        binding.btnSelectAll.setOnClickListener {
            viewModel.toggleSelectAll()
        }

        // 设置确认按钮点击事件
        binding.fabConfirm.setOnClickListener {
            viewModel.onConfirmClick()
        }

        // 观察导航事件
        viewModel.navigateToResult.observe(this) { selectedApps ->
            if (selectedApps.isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putStringArrayListExtra("selectedApps", ArrayList(selectedApps))
                }
                startActivity(intent)
            }
        }

        // 观察应用列表变化
        viewModel.apps.observe(this) { apps ->
            adapter.submitList(apps)
        }
    }
} 