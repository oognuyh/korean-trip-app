package com.example.trip.ui.home.adapter

import com.example.trip.R
import com.example.trip.databinding.ItemCourseBinding
import com.example.trip.model.tour.Content
import com.example.trip.ui.base.BaseRVAdapter

class CourseAdapter : BaseRVAdapter<ItemCourseBinding, Content>() {
    override fun bind(holder: ViewHolder, position: Int) {
        holder.binding.course = items[position]
    }

    override fun getLayoutResId() = R.layout.item_course
}