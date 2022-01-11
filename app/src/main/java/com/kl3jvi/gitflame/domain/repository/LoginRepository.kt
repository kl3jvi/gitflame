package com.kl3jvi.gitflame.domain.repository

import com.kl3jvi.gitflame.data.model.AccessTokenModel

interface LoginRepository {
    suspend fun getAccessToken(
        code: String,
        clientId: String,
        clientSecret: String,
        state: String,
        redirectUrl: String
    ): AccessTokenModel
}