package com.kl3jvi.gitflame.domain.use_case.get_access_token

import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.AccessTokenModel
import com.kl3jvi.gitflame.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(
        code: String,
        clientId: String,
        clientSecret: String,
        state: String,
        redirectUrl: String
    ): Flow<Resource<AccessTokenModel>> {
        return repository.getAccessToken(
            code,
            clientId,
            clientSecret,
            state,
            redirectUrl
        ).flowOn(ioDispatcher)
    }
}