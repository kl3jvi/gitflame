package com.kl3jvi.gitflame.domain.repository

import androidx.paging.PagingData
import com.kl3jvi.gitflame.common.network_state.Resource
import com.kl3jvi.gitflame.data.remote.dto.EventModelItem
import com.kl3jvi.gitflame.data.remote.dto.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(accessToken: String): Flow<Resource<UserModel>>
    fun getReceivedEvents(username: String): Flow<PagingData<EventModelItem>>
}