package com.kl3jvi.gitflame.data.persistence

import com.kl3jvi.gitflame.data.model.AccessTokenModel
import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    suspend fun saveTokenToPreferencesStore(token: String)
    fun getTokenFromPreferencesStore(): Flow<String>
}