package com.example.trip.ui.home.adapter

import com.example.trip.R
import com.example.trip.databinding.ItemFestivalBinding
import com.example.trip.model.tour.Content
import com.example.trip.ui.base.BaseRVAdapter

class FestivalAdapter : BaseRVAdapter<ItemFestivalBinding, Content>() {
    override fun bind(holder: ViewHolder, position: Int) {
        holder.binding.festival = items[position]
    }

    override fun getLayoutResId() = R.layout.item_festival
}