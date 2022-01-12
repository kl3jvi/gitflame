package com.kl3jvi.gitflame.data.repository

import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.UserModel
import com.kl3jvi.gitflame.data.network.UserService
import com.kl3jvi.gitflame.domain.repository.NetworkBoundRepository
import com.kl3jvi.gitflame.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override fun getUser(): Flow<Resource<UserModel>> {
        return object : NetworkBoundRepository<UserModel>() {
            override suspend fun fetchFromRemote(): Response<UserModel> = userService.getUser()
        }.asFlow()
    }
}