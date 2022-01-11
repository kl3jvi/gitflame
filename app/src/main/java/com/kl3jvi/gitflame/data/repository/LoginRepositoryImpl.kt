package com.kl3jvi.gitflame.data.repository

import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.AccessTokenModel
import com.kl3jvi.gitflame.data.network.LoginService
import com.kl3jvi.gitflame.domain.repository.LoginRepository
import com.kl3jvi.gitflame.domain.repository.NetworkBoundRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRepository {
    override fun getAccessToken(
        code: String,
        clientId: String,
        clientSecret: String,
        state: String,
        redirectUrl: String
    ): Flow<Resource<AccessTokenModel>> {
        return object : NetworkBoundRepository<AccessTokenModel>() {
            override suspend fun fetchFromRemote(): Response<AccessTokenModel> =
                loginService.getAccessToken(code, clientId, clientSecret, state, redirectUrl)
        }.asFlow()
    }
}