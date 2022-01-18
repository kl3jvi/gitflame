package com.kl3jvi.gitflame.bindings

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
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
    @BindingAdapter("drawable")
    fun setDrawable(image: ImageView, drawable: Drawable?) {
        if (drawable != null) {
            image.load(drawable) {
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
                diskCachePolicy(CachePolicy.ENABLED)
                transformations(CircleCropTransformation())
            }
        }
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun bindVisibility(view: View, visible: Boolean?) {
        view.isVisible = visible == true
    }
}


