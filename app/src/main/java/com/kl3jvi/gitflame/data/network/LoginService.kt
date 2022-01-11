package com.kl3jvi.gitflame.data.network

import com.kl3jvi.gitflame.data.model.AccessTokenModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("access_token")
    @Headers("Accept: application/json")
    suspend fun getAccessToken(
        @Field("code") code: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("state") state: String,
        @Field("redirect_uri") redirectUrl: String
    ): AccessTokenModel
}