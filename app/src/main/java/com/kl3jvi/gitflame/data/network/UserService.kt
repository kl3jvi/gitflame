package com.kl3jvi.gitflame.data.network

import com.kl3jvi.gitflame.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("user")
    suspend fun getUser(): Response<UserModel>
}



