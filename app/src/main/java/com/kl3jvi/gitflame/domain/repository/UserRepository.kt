package com.kl3jvi.gitflame.domain.repository

import androidx.paging.PagingData
import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.EventModel
import com.kl3jvi.gitflame.data.model.EventModelItem
import com.kl3jvi.gitflame.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {
    fun getUser(accessToken: String): Flow<Resource<UserModel>>
    fun getReceivedEvents(username: String): Flow<PagingData<EventModelItem>>
}