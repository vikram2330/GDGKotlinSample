package com.vikram.gdgkotlinsample.ui.extensions

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImageForUrl(iconUrl: String) {
    Glide.with(context)
        .load(iconUrl)
        .centerCrop()
        .into(this)
}

fun View?.setVisible(visibility: Boolean) {
    this?.let {
        if (visibility) {
            setVisibility(View.VISIBLE)
        } else {
            setVisibility(View.GONE)
        }
    }
}