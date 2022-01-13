package com.kl3jvi.gitflame.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class EventModel : ArrayList<EventModelItem>()

@JsonClass(generateAdapter = true)
data class EventModelItem(
    @Json(name = "actor")
    val actor: Actor,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "org")
    val org: Org,
    @Json(name = "payload")
    val payload: Payload,
    @Json(name = "public")
    val `public`: Boolean,
    @Json(name = "repo")
    val repo: Repo,
    @Json(name = "type")
    val type: String
)

@JsonClass(generateAdapter = true)
data class Org(
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "gravatar_id")
    val gravatarId: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "login")
    val login: String,
    @Json(name = "url")
    val url: String
)

@JsonClass(generateAdapter = true)
data class Payload(
    @Json(name = "action")
    val action: String
)


@JsonClass(generateAdapter = true)
data class Repo(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)