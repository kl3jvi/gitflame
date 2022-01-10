package com.kl3jvi.gitflame.data.model

data class AccessTokenModel(
    var id: Long = 0,
    val token: String? = null,
    val hashedToken: String? = null,
    val accessToken: String? = null,
    val tokenType: String? = null
)
