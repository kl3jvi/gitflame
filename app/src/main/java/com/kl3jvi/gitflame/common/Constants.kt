package com.kl3jvi.gitflame.common

import com.kl3jvi.gitflame.BuildConfig

object Constants {
    const val BASE_URL = "https://github.com/"
    const val BASE_API_URL = "https://api.github.com/"
    const val CLIENT_ID = BuildConfig.GIT_ID
    const val CLIENT_SECRET = BuildConfig.GIT_SECRET
    const val REDIRECT_URL = BuildConfig.REDIRECT_URI
    const val APPLICATION_ID = BuildConfig.APPLICATION_ID
    const val STARTING_PAGE_INDEX = 1
    fun emptyString() = ""
}