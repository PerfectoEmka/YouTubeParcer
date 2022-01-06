package com.example.kotlinl5.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String) {
    Glide.with(this).load(url).into(this)
}

fun View.visibility(boolean: Boolean){
    visibility = if (boolean) View.VISIBLE else View.GONE
}
