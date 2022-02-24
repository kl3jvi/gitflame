package com.kl3jvi.gitflame.data.remote.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class AccessTokenModel(
    @field:Json(name = "id") var id: Long = 0,
    @field:Json(name = "token") val token: String? = null,
    @field:Json(name = "hashed_token") val hashedToken: String? = null,
    @field:Json(name = "access_token") val accessToken: String? = null,
    @field:Json(name = "token_type") val tokenType: String? = null
) : Parcelable

