package com.kl3jvi.gitflame.presentation.ui.profile

import androidx.lifecycle.ViewModel
import com.kl3jvi.gitflame.common.utils.mapToState
import com.kl3jvi.gitflame.domain.use_case.get_user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    fun getUser(accessToken: String) = getUserUseCase(accessToken = accessToken).mapToState()
}