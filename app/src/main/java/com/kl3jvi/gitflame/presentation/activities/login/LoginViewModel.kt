package com.kl3jvi.gitflame.presentation.activities.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kl3jvi.gitflame.common.launchOnDefault
import com.kl3jvi.gitflame.common.mapToState
import com.kl3jvi.gitflame.data.persistence.DataStoreManager
import com.kl3jvi.gitflame.domain.use_case.get_access_token.GetAccessTokenUseCase
import com.kl3jvi.gitflame.domain.use_case.get_user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val dataStoreManagerImpl: DataStoreManager
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

    fun saveTokenToDataStore(token: String) = viewModelScope.launchOnDefault {
        dataStoreManagerImpl.saveTokenToPreferencesStore(token)
    }

    fun getUser(accessToken: String) = getUserUseCase(accessToken = accessToken).mapToState()
}

