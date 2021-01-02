package com.example.trip.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.trip.R
import com.example.trip.ui.base.BaseRVAdapter

@BindingAdapter("imageUrl", "progressBar")
fun setImageFromUrl(imageView: ImageView, imageUrl: String?, progressBar: ProgressBar) {
    Glide.with(imageView.context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.no_image)
            .fallback(R.drawable.no_image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    progressBar.hide()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    progressBar.hide()
                    return false
                }
            })
            .thumbnail(0.1f)
            .into(imageView)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items")
fun<T> setRecyclerViewItems(recyclerView: RecyclerView, items: List<T>?) {
    if (items == null) return

    recyclerView.adapter?.let { adapter ->
        adapter as BaseRVAdapter<*, T>
        adapter.items = items
    }
}