package com.kl3jvi.gitflame.data.model


import android.content.Context
import com.kl3jvi.gitflame.domain.model.EventType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EventModelItem(
    @field:Json(name = "actor")
    val actor: Actor,
    @field:Json(name = "created_at")
    val createdAt: String?,
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "org")
    val org: Org?,
    @field:Json(name = "payload")
    val payload: Payload,
    @field:Json(name = "public")
    val `public`: Boolean,
    @field:Json(name = "repo")
    val repo: Repo,
    @field:Json(name = "type")
    val type: EventType?
) {

}


@JsonClass(generateAdapter = true)
data class Org(
    @field:Json(name = "avatar_url")
    val avatarUrl: String?,
    @field:Json(name = "gravatar_id")
    val gravatarId: String?,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "login")
    val login: String?,
    @field:Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class Payload(
    @field:Json(name = "action")
    val action: String?
)


@JsonClass(generateAdapter = true)
data class Repo(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class Actor(
    @field:Json(name = "avatar_url")
    val avatarUrl: String?,
    @field:Json(name = "display_login")
    val displayLogin: String?,
    @field:Json(name = "gravatar_id")
    val gravatarId: String?,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "login")
    val login: String?,
    @field:Json(name = "url")
    val url: String?
)