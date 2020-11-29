package com.example.trip.ui.around.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trip.R
import com.example.trip.model.LocationBasedContent
import com.example.trip.utils.loadImage
import kotlinx.android.synthetic.main.item_around_content.view.*

class AroundContentRecyclerViewAdapter(private val context: Context, private val contents: List<LocationBasedContent>) : RecyclerView.Adapter<AroundContentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_around_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contents[position])
    }

    override fun getItemCount() = contents.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.tv_around_content_title
        val address = view.tv_around_content_address
        val progressBar = view.pb_around_content
        val image = view.iv_around_content_image

        fun bind(content: LocationBasedContent) {
            context.loadImage(content.firstimage, image, progressBar)
            title.text = content.title
            address.text = content.addr1
        }
    }
}