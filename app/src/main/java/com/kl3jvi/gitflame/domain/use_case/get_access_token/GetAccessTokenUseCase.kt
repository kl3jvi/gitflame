package com.kl3jvi.gitflame.domain.use_case.get_access_token

import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.AccessTokenModel
import com.kl3jvi.gitflame.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
//    operator fun invoke(): Flow<Resource<AccessTokenModel>> = flow {
//        return loginRepository.getAccessToken().flowOn()
//    }
}