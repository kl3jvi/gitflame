package com.kl3jvi.gitflame.data.repository

import com.kl3jvi.gitflame.common.network_state.Resource
import com.kl3jvi.gitflame.data.remote.dto.AccessTokenModelDto
import com.kl3jvi.gitflame.data.remote.network.LoginService
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
    ): Flow<Resource<AccessTokenModelDto>> {
        return object : NetworkBoundRepository<AccessTokenModelDto>() {
            override suspend fun fetchFromRemote(): Response<AccessTokenModelDto> =
                loginService.getAccessToken(
                    code,
                    clientId,
                    clientSecret,
                    state,
                    redirectUrl
                )
        }.asFlow()
    }
}