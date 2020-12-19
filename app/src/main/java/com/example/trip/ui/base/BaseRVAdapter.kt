package com.example.trip.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRVAdapter<B: ViewBinding, T> : RecyclerView.Adapter<BaseRVAdapter<B, T>.ViewHolder>() {
    var items: List<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = getRecyclerViewBinding(LayoutInflater.from(parent.context), parent)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bind(holder, position)
    }

    fun replaceItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items?.size ?: 0

    abstract fun bind(holder: ViewHolder, position: Int)

    abstract fun getRecyclerViewBinding(inflater: LayoutInflater, parent: ViewGroup): B

    inner class ViewHolder(val binding: B) : RecyclerView.ViewHolder(binding.root)
}