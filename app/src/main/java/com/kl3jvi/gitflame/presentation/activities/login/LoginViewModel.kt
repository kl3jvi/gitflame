package com.kl3jvi.gitflame.presentation.activities.login

import androidx.lifecycle.ViewModel
import com.kl3jvi.gitflame.common.State
import com.kl3jvi.gitflame.domain.use_case.get_access_token.GetAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {

    fun getAccessToken(
        code: String,
        clientId: String,
        clientSecret: String,
        state: String,
        redirectUrl: String
    ) = getAccessTokenUseCase(
        code,
        clientId,
        clientSecret,
        state,
        redirectUrl
    ).map { resource -> State.fromResource(resource) }

}