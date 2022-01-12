package com.kl3jvi.gitflame.presentation.activities.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kl3jvi.gitflame.common.State
import com.kl3jvi.gitflame.common.launchOnDefault
import com.kl3jvi.gitflame.data.persistence.DataStoreManager
import com.kl3jvi.gitflame.domain.use_case.get_access_token.GetAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
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
    ).map { resource -> State.fromResource(resource) }

    fun saveTokenToDataStore(token: String) = viewModelScope.launchOnDefault {
        dataStoreManagerImpl.saveTokenToPreferencesStore(token)
    }

//    fun getUser()
}

