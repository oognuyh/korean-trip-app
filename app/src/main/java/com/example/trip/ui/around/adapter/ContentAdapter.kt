package com.example.trip.ui.around.adapter

import com.example.trip.R
import com.example.trip.databinding.ItemNearbyContentBinding
import com.example.trip.model.tour.Content
import com.example.trip.ui.base.BaseRVAdapter

class ContentAdapter : BaseRVAdapter<ItemNearbyContentBinding, Content>() {
    override fun bind(holder: ViewHolder, position: Int) {
        holder.binding.content = items[position]
    }

    override fun getLayoutResId() = R.layout.item_nearby_content
}