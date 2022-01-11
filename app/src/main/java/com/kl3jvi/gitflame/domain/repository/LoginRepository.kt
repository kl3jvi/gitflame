package com.kl3jvi.gitflame.domain.repository

import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.AccessTokenModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun getAccessToken(
        code: String,
        clientId: String,
        clientSecret: String,
        state: String,
        redirectUrl: String
    ): Flow<Resource<AccessTokenModel>>
}