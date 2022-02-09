package com.kl3jvi.gitflame.presentation.ui.profile

import androidx.lifecycle.ViewModel
import com.kl3jvi.gitflame.common.utils.mapToState
import com.kl3jvi.gitflame.data.persistence.DataStoreManager
import com.kl3jvi.gitflame.domain.use_case.get_user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private fun getToken() = dataStoreManager.getTokenFromPreferencesStore()

    fun getUser() = getToken().flatMapLatest {
        getUserUseCase(accessToken = it).mapToState()
    }
}