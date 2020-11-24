package com.example.trip.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(text: CharSequence) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

fun Context.toast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.loadImage(url: String, imageView: ImageView) {
    Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .thumbnail(0.1f)
            .into(imageView)
}