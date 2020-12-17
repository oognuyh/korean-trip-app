package com.example.trip.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<B: ViewDataBinding, T> : RecyclerView.Adapter<BaseRecyclerViewAdapter<B, T>.ViewHolder>() {
    var items: List<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutId(),
                parent,
                false
        ) as B

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        DataBindingUtil.bind<B>(holder.itemView)?.let { binding ->
            bind(binding, position)
        }
    }

    override fun getItemCount() = items?.size ?: 0

    abstract fun bind(binding: B, position: Int)

    @LayoutRes
    abstract fun getLayoutId(): Int

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}