package com.kl3jvi.gitflame.domain.use_case.get_user_feed

import androidx.paging.PagingData
import com.kl3jvi.gitflame.data.model.EventModelItem
import com.kl3jvi.gitflame.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserFeedUseCase @Inject constructor(
    private val repository: UserRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(username: String): Flow<PagingData<EventModelItem>> =
        repository.getReceivedEvents(username = username).flowOn(ioDispatcher)
}