package com.tyhoo.android.trad.pmd.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tyhoo.android.trad.pmd.util.GlideApp

@BindingAdapter(value = ["newsImageFromUrl"])
fun bindNewsImageFromUrl(view: ImageView, imageUrl: String?) {
    imageUrl?.let { url ->
        GlideApp.with(view.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}