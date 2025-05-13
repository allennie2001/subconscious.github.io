package com.subconscious.anotherme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.subconscious.anotherme.adapter.viewmodel.MainViewModel
import com.subconscious.anotherme.databinding.ItemAppBinding

class AppAdapter(private val viewModel: MainViewModel) : ListAdapter<String, AppAdapter.AppViewHolder>(AppDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class AppViewHolder(private val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appName: String, position: Int) {
            binding.appName = appName
            binding.isSelected = viewModel.isSelected(position)
            
            // 设置点击事件
            binding.onClick = android.view.View.OnClickListener {
                viewModel.onAppSelected(position)
                binding.isSelected = viewModel.isSelected(position)
            }
            
            // 观察选中状态的变化
            viewModel.selectedPositions.observeForever { selectedPositions ->
                binding.isSelected = selectedPositions.contains(position)
            }
        }
    }

    private class AppDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}