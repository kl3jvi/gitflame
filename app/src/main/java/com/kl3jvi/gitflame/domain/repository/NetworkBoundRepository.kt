package com.kl3jvi.gitflame.domain.repository

import androidx.annotation.MainThread
import com.kl3jvi.gitflame.common.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class NetworkBoundRepository<RESULT> {
    fun asFlow() = flow<Resource<RESULT>> {

        // Emit loading state first before making network call
        emit(Resource.Loading<RESULT>())

        // Fetch remote content and parse body
        val response = fetchFromRemote()
        val body = response.body()

        if (!response.isSuccessful && body == null)
            emit(Resource.Failed(response.message()))
        else if (body != null) emit(
            Resource.Success<RESULT>(body)
        )

    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network Error Happened!"))
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}