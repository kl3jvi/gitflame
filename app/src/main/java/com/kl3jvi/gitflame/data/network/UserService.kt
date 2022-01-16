package com.kl3jvi.gitflame.data.network

import com.kl3jvi.gitflame.data.model.EventModelItem
import com.kl3jvi.gitflame.data.model.UserModel
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
}