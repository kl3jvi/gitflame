package com.kl3jvi.gitflame.data.network.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kl3jvi.gitflame.common.Constants.STARTING_PAGE_INDEX
import com.kl3jvi.gitflame.data.model.EventModelItem
import com.kl3jvi.gitflame.data.network.UserService

class FeedPagingSource(
    private val userService: UserService,
    private val username: String
) : PagingSource<Int, EventModelItem>() {
    override fun getRefreshKey(state: PagingState<Int, EventModelItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventModelItem> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val eventResponse = userService.getReceivedEvents(username, page)
            LoadResult.Page(
                data = eventResponse,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (eventResponse.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            LoadResult.Error(exception)
        }
    }
}