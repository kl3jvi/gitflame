package com.kl3jvi.gitflame.common

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failed<T>(val message: String) : Resource<T>()
    class Loading<T>(val data: T? = null) : Resource<T>()
}