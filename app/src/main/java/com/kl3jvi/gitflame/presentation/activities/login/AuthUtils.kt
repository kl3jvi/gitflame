package com.kl3jvi.gitflame.presentation.activities.login

import android.content.Intent
import android.net.Uri
import com.kl3jvi.gitflame.data.model.AccessTokenModel

interface AuthUtils {

    fun getAuthorizationUrl(): Uri

    fun onHandleAuthIntent(intent: Intent?)

    fun onTokenResponse(response: AccessTokenModel?)

//    fun onUserResponse(response: Login?)

    fun login(
        username: String,
        password: String,
        twoFactorCode: String?,
        isBasicAuth: Boolean,
        endpoint: String?
    )
}