package com.example.trip.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.trip.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun View.snackbar(text: CharSequence) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

fun Context.toast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.hide() {
    visibility = ProgressBar.GONE
}

fun setImageFromUrl(imageUrl: String?, imageView: ImageView, progressBar: ProgressBar) {
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

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

inline fun <reified T> genericType(): Type = object: TypeToken<T>() {}.type