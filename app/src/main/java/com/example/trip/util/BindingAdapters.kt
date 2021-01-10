package com.example.trip.util

import android.graphics.drawable.Drawable
import android.os.Handler
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.trip.R
import com.example.trip.ui.base.BaseRVAdapter
import com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

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

@BindingAdapter("indicator")
fun setViewPagerWithIndicator(viewPager2: ViewPager2, dotsIndicator: DotsIndicator) {
    viewPager2.adapter?.let {
        dotsIndicator.setViewPager2(viewPager2)
    }
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

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items")
fun<T> setRecyclerViewItems(viewPager2: ViewPager2, items: List<T>?) {
    if (items == null) return

    viewPager2.adapter?.let { adapter ->
        adapter as BaseRVAdapter<*, T>
        adapter.items = items
    }
}

@BindingAdapter("setAutoScroll")
fun setViewPager2WithAutoScroll(viewPager2: ViewPager2, isOn: Boolean) {
    if (isOn) {
        val scrollHandler = Handler()
        val nextPage = Runnable {
            viewPager2.currentItem = if (viewPager2.currentItem == (viewPager2.adapter as BaseRVAdapter<*, *>).itemCount - 1) 0 else viewPager2.currentItem + 1
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                scrollHandler.removeCallbacks(nextPage)
                scrollHandler.postDelayed(nextPage, 3000)
            }
        })
    }
}