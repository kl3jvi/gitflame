package com.kl3jvi.gitflame.presentation.activities.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kl3jvi.gitflame.common.utils.Constants.AUTHENTICATION_TOKEN
import com.kl3jvi.gitflame.common.utils.Constants.emptyString
import com.kl3jvi.gitflame.common.utils.launchOnIo
import com.kl3jvi.gitflame.common.utils.mapToState
import com.kl3jvi.gitflame.data.persistence.DataStoreManager
import com.kl3jvi.gitflame.domain.use_case.get_access_token.GetAccessTokenUseCase
import com.kl3jvi.gitflame.domain.use_case.get_user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val dataStoreManager: DataStoreManager,
    private val state: SavedStateHandle
) : ViewModel() {

    private val tokenFromState = state.get<String>(AUTHENTICATION_TOKEN) ?: emptyString()

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

    fun saveTokenToDataStore(token: String) = launchOnIo {
        dataStoreManager.saveTokenToPreferencesStore(token)
    }

    fun getToken() = dataStoreManager.getTokenFromPreferencesStore()

    fun getUser(accessToken: String) = getUserUseCase(accessToken = accessToken).mapToState()
}



