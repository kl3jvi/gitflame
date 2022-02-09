package com.kl3jvi.gitflame.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kl3jvi.gitflame.common.network_state.Resource
import com.kl3jvi.gitflame.data.remote.dto.EventModelItem
import com.kl3jvi.gitflame.data.remote.dto.UserModel
import com.kl3jvi.gitflame.data.remote.network.UserService
import com.kl3jvi.gitflame.data.remote.network.pagination.FeedPagingSource
import com.kl3jvi.gitflame.domain.repository.NetworkBoundRepository
import com.kl3jvi.gitflame.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override fun getUser(accessToken: String): Flow<Resource<UserModel>> {
        return object : NetworkBoundRepository<UserModel>() {
            override suspend fun fetchFromRemote(): Response<UserModel> =
                userService.getUser("Bearer $accessToken")
        }.asFlow()
    }

    override fun getReceivedEvents(
        username: String
    ): Flow<PagingData<EventModelItem>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { FeedPagingSource(userService, username) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30 // Default is 30 , MAX = 100
    }
}