package com.kl3jvi.gitflame.data.model


import com.kl3jvi.gitflame.domain.model.EventType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventModel(
    val results: List<EventModelItem>
)

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
    fun eventType(): Int {
        return when (type) {
            EventType.WatchEvent -> EventType.WatchEvent.event
            EventType.CreateEvent -> EventType.CreateEvent.event
            EventType.CommitCommentEvent -> EventType.CommitCommentEvent.event
            EventType.DownloadEvent -> EventType.DownloadEvent.event
            EventType.FollowEvent -> EventType.FollowEvent.event
            EventType.ForkEvent -> EventType.ForkEvent.event
            EventType.GistEvent -> EventType.GistEvent.event
            EventType.GollumEvent -> EventType.GollumEvent.event
            EventType.IssueCommentEvent -> EventType.IssueCommentEvent.event
            EventType.IssuesEvent -> EventType.IssuesEvent.event
            EventType.MemberEvent -> EventType.MemberEvent.event
            EventType.PublicEvent -> EventType.PublicEvent.event
            EventType.PullRequestEvent -> EventType.PullRequestEvent.event
            EventType.PullRequestReviewCommentEvent -> EventType.PullRequestReviewCommentEvent.event
            EventType.PullRequestReviewEvent -> EventType.PullRequestReviewEvent.event
            EventType.RepositoryEvent -> EventType.RepositoryEvent.event
            EventType.PushEvent -> TODO()
            EventType.TeamAddEvent -> TODO()
            EventType.DeleteEvent -> TODO()
            EventType.ReleaseEvent -> TODO()
            EventType.ForkApplyEvent -> TODO()
            EventType.OrgBlockEvent -> TODO()
            EventType.ProjectCardEvent -> TODO()
            EventType.ProjectColumnEvent -> TODO()
            EventType.OrganizationEvent -> TODO()
            EventType.ProjectEvent -> TODO()
            null -> TODO()
        }
    }
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