package com.kl3jvi.gitflame.presentation.activities.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kl3jvi.gitflame.common.network_state.Resource
import com.kl3jvi.gitflame.common.utils.launchOnIo
import com.kl3jvi.gitflame.common.utils.mapToState
import com.kl3jvi.gitflame.data.persistence.LocalStorage
import com.kl3jvi.gitflame.domain.use_case.get_access_token.GetAccessTokenUseCase
import com.kl3jvi.gitflame.domain.use_case.get_user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val localStorage: LocalStorage
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
    ).mapToState()

    fun saveTokenToDataStore(token: String) {
        localStorage.authToken = token
    }

    fun getToken() = localStorage.authToken

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launchOnIo {
            getUserUseCase(accessToken = localStorage.authToken ?: "").collect {
                when (it) {
                    is Resource.Failed -> Log.e("Error getting username", it.message)
                    is Resource.Success -> localStorage.userName = it.data.login
                }
            }
        }
    }

}
