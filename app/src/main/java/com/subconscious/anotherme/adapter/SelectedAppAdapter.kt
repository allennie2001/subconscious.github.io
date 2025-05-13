package com.subconscious.anotherme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subconscious.anotherme.databinding.ItemSelectedAppBinding

class SelectedAppAdapter : RecyclerView.Adapter<SelectedAppAdapter.SelectedAppViewHolder>() {

    var data: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedAppViewHolder {
        val binding =
            ItemSelectedAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectedAppViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: SelectedAppViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class SelectedAppViewHolder(private val binding: ItemSelectedAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(appName: String) {
            binding.appName.text = appName
        }
    }

} 