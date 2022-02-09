package com.kl3jvi.gitflame.data.remote.network

import com.kl3jvi.gitflame.data.remote.dto.AccessTokenModelDto
import com.kl3jvi.gitflame.data.remote.dto.UserModel
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @GET("user")
    suspend fun loginAccessToken(): Response<UserModel>

//    @POST("authorizations")
//    fun login(@Body authModel: AuthModel): Observable<AccessTokenModel?>?

    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("code") code: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("state") state: String,
        @Field("redirect_uri") redirectUrl: String
    ): Response<AccessTokenModelDto>
}