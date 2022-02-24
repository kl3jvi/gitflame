package com.kl3jvi.gitflame.presentation.activities.login

import android.content.Intent
import android.net.Uri
import com.kl3jvi.gitflame.data.remote.dto.AccessTokenModel

interface Authentication {
    fun getAuthorizationUrl(): Uri
    fun onHandleAuthIntent(intent: Intent?)
    fun onTokenResponse(response: AccessTokenModel?)
}