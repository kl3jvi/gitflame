package com.kl3jvi.gitflame.presentation.activities.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kl3jvi.gitflame.common.utils.launchOnIo
import com.kl3jvi.gitflame.common.utils.mapToState
import com.kl3jvi.gitflame.data.persistence.DataStoreManager
import com.kl3jvi.gitflame.domain.use_case.get_access_token.GetAccessTokenUseCase
import com.kl3jvi.gitflame.domain.use_case.get_user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val dataStoreManager: DataStoreManager,
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

    fun saveTokenToDataStore(token: String) = launchOnIo {
        dataStoreManager.saveTokenToPreferencesStore(token)
    }

    fun getToken() = dataStoreManager.getTokenFromPreferencesStore()

    fun getUser() = getToken().flatMapLatest {
        getUserUseCase(accessToken = it).mapToState()
    }
}
