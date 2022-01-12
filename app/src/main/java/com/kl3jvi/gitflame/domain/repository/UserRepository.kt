package com.kl3jvi.gitflame.domain.repository

import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<Resource<UserModel>>
}