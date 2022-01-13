package com.kl3jvi.gitflame.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Actor(
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "display_login")
    val displayLogin: String,
    @Json(name = "gravatar_id")
    val gravatarId: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "login")
    val login: String,
    @Json(name = "url")
    val url: String
)