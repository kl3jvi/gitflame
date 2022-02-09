package com.kl3jvi.gitflame.domain.repository

import com.kl3jvi.gitflame.common.network_state.Resource
import com.kl3jvi.gitflame.data.remote.dto.AccessTokenModelDto
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun getAccessToken(
        code: String,
        clientId: String,
        clientSecret: String,
        state: String,
        redirectUrl: String
    ): Flow<Resource<AccessTokenModelDto>>
}