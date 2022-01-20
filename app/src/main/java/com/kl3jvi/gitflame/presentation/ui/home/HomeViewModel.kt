package com.kl3jvi.gitflame.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kl3jvi.gitflame.data.persistence.DataStoreManager
import com.kl3jvi.gitflame.domain.use_case.get_user_feed.GetUserFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserFeedUseCase: GetUserFeedUseCase,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    fun getUserFeed(username: String) = getUserFeedUseCase(username)
    fun getToken() = dataStoreManager.getTokenFromPreferencesStore()
}