package com.kl3jvi.gitflame.domain.repository

import android.util.Log
import androidx.annotation.MainThread
import com.kl3jvi.gitflame.common.network_state.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class NetworkBoundRepository<RESULT> {
    fun asFlow() = flow<Resource<RESULT>> {

        // Fetch remote content and parse body
        val response = fetchFromRemote()
        val body = response.body()
        Log.e("Response", body.toString())

        // emit response in case it is successful
        if (!response.isSuccessful && body == null)
            emit(Resource.Failed<RESULT>(response.message()))
        else if (body != null)
            emit(Resource.Success<RESULT>(body))
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network Error Happened!"))
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}