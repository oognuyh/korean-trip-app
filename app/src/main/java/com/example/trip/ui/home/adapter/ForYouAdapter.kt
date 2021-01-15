package com.example.trip.ui.home.adapter

import com.example.trip.R
import com.example.trip.databinding.ItemContentBinding
import com.example.trip.model.tour.Content
import com.example.trip.ui.base.BaseRVAdapter

class ForYouAdapter : BaseRVAdapter<ItemContentBinding, Content>() {
    override fun bind(holder: ViewHolder, position: Int) {
        holder.binding.content = items[position]
    }

    override fun getLayoutResId() = R.layout.item_content
}