package com.kl3jvi.gitflame.domain.repository

import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.EventModel
import com.kl3jvi.gitflame.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(accessToken: String): Flow<Resource<UserModel>>
    fun getReceivedEvents(username: String): Flow<Resource<EventModel>>
}