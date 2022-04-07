package com.kl3jvi.gitflame.presentation.ui.profile

import androidx.lifecycle.ViewModel
import com.kl3jvi.gitflame.common.utils.mapToState
import com.kl3jvi.gitflame.data.persistence.LocalStorage
import com.kl3jvi.gitflame.domain.use_case.get_user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val localStorage: LocalStorage
) : ViewModel() {

    private fun getToken() = localStorage.authToken ?: ""

    fun getUser() = getUserUseCase(getToken()).mapToState()

}