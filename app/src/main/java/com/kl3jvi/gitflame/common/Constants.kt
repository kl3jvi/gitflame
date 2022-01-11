package com.kl3jvi.gitflame.common

import com.kl3jvi.gitflame.BuildConfig

object Constants {
    const val BASE_LOGIN_URL = "https://github.com/login/oauth/"

    const val CLIENT_ID = "?client_id=${BuildConfig.GIT_ID}"
}