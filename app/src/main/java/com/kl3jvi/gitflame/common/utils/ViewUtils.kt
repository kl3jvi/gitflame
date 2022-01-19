package com.kl3jvi.gitflame.common.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showSnack(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(view, message, duration)
    if (!snack.isShown) {
        snack.show()
    }
}

fun Activity.showSnack(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(view, message, duration)
    if (!snack.isShown) {
        snack.show()
    }
}