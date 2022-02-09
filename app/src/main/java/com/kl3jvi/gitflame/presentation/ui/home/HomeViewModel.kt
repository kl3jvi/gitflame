package com.kl3jvi.gitflame.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kl3jvi.gitflame.domain.use_case.get_user_feed.GetUserFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserFeedUseCase: GetUserFeedUseCase,
) : ViewModel() {
    fun getUserFeed(username: String) = getUserFeedUseCase(username).cachedIn(viewModelScope)
}