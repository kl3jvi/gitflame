package com.kl3jvi.gitflame.domain.use_case.get_user

import com.kl3jvi.gitflame.common.Resource
import com.kl3jvi.gitflame.data.model.UserModel
import com.kl3jvi.gitflame.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<Resource<UserModel>> = repository.getUser().flowOn(ioDispatcher)
}