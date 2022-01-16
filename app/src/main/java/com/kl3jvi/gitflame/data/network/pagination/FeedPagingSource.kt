package com.kl3jvi.gitflame.data.network.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kl3jvi.gitflame.common.Constants.STARTING_PAGE_INDEX
import com.kl3jvi.gitflame.data.model.EventModelItem
import com.kl3jvi.gitflame.data.network.UserService
import javax.inject.Inject

class FeedPagingSource @Inject constructor(
    private val userService: UserService,
    private val username: String
) : PagingSource<Int, EventModelItem>() {
    override fun getRefreshKey(state: PagingState<Int, EventModelItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventModelItem> {

        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = userService.getReceivedEvents(username, page)
            val photos = response.results
            LoadResult.Page(
                data = photos,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == MAXIMUM_PAGES) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val MAXIMUM_PAGES = 5
    }
}