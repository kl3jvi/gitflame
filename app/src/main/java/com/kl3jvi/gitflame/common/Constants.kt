package com.kl3jvi.gitflame.common

import com.kl3jvi.gitflame.BuildConfig

object Constants {
    const val OAUTH_URL = "https://github.com/login/oauth/authorize"
    const val CLIENT_ID = "?client_id=${BuildConfig.GIT_ID}"
}