package com.kl3jvi.gitflame.data.network

import com.kl3jvi.gitflame.data.model.AccessTokenModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

//    @GET("user")
//    fun loginAccessToken(): Observable<Login?>?
//
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
    ): Response<AccessTokenModel>
}