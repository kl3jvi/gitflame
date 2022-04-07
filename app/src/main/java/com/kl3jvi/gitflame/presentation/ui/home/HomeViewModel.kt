package com.kl3jvi.gitflame.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kl3jvi.gitflame.common.utils.launchOnIo
import com.kl3jvi.gitflame.data.persistence.LocalStorage
import com.kl3jvi.gitflame.data.remote.dto.EventModelItem
import com.kl3jvi.gitflame.domain.use_case.get_user_feed.GetUserFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserFeedUseCase: GetUserFeedUseCase,
    private val localStorage: LocalStorage
) : ViewModel() {

    private val username = localStorage.userName ?: ""

    private var _userFeed = MutableStateFlow<PagingData<EventModelItem>>(PagingData.empty())
    var usersFeed = _userFeed.asStateFlow()

    init {
        getUserFeed()
    }

    private fun getUserFeed() {
        viewModelScope.launchOnIo {
            getUserFeedUseCase(username).cachedIn(viewModelScope).collect { data ->
                _userFeed.value = data
            }
        }
    }
}
