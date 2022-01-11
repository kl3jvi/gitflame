package com.kl3jvi.gitflame.data.repository

import com.kl3jvi.gitflame.data.model.AccessTokenModel
import com.kl3jvi.gitflame.data.network.LoginService
import com.kl3jvi.gitflame.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRepository {
    override suspend fun getAccessToken(
        code: String,
        clientId: String,
        clientSecret: String,
        state: String,
        redirectUrl: String
    ): AccessTokenModel =
        loginService.getAccessToken(code, clientId, clientSecret, state, redirectUrl)

}