package com.example.trip.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRVAdapter<B: ViewDataBinding, T> : RecyclerView.Adapter<BaseRVAdapter<B, T>.ViewHolder>() {
    var items: List<T> = emptyList()
        set(_items) {
            field = _items
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), getLayoutResId(), parent, false) as B

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bind(holder, position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size

    abstract fun bind(holder: ViewHolder, position: Int)

    @LayoutRes
    abstract fun getLayoutResId(): Int

    inner class ViewHolder(val binding: B) : RecyclerView.ViewHolder(binding.root)
}