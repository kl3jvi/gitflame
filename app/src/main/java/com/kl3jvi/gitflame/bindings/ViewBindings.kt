package com.kl3jvi.gitflame.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation

object ViewBindings {
    @JvmStatic
    @BindingAdapter("image")
    fun setImage(image: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            image.load(url) {
                crossfade(true)
                diskCachePolicy(CachePolicy.ENABLED)
            }
        }
    }


    @JvmStatic
    @BindingAdapter("avatarImage")
    fun setAvatarImage(image: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            image.load(url) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }
}


