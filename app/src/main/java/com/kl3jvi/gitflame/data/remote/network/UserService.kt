package com.kl3jvi.gitflame.data.remote.network

import android.app.appsearch.SearchResult
import com.kl3jvi.gitflame.data.remote.dto.EventModelItem
import com.kl3jvi.gitflame.data.remote.dto.Repo
import com.kl3jvi.gitflame.data.remote.dto.UserModel
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("user")
    suspend fun getUser(
        @Header("Authorization") auth: String?
    ): Response<UserModel>

    @GET("users/{username}/received_events")
    suspend fun getReceivedEvents(
        @Path("username") userName: String,
        @Query("page") page: Int
    ): List<EventModelItem>

    @GET("users/{username}/repos?")
    suspend fun getUserRepositories(
        @Path("username") username: String,
        @Query("page") pageIndex: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String,
    ): List<Repo>


    @GET("search/repositories")
    suspend fun search(
        @Query("q") q: String,
        @Query("page") pageIndex: Int,
        @Query("per_page") perPage: Int
    ): SearchResult
}