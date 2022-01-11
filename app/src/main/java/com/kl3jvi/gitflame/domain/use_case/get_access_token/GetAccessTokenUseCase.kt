package com.kl3jvi.gitflame.domain.use_case.get_access_token

import com.kl3jvi.gitflame.domain.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    operator fun invoke() = flow<>{

    }
}