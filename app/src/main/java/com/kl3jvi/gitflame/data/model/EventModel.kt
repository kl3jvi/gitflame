package com.kl3jvi.gitflame.data.model


import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.Constants.emptyString
import com.kl3jvi.gitflame.common.DateParser
import com.kl3jvi.gitflame.common.DateParser.Companion.getTimeAgo
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
    fun getEventType(context: Context): String {
        return if (type != null) {
            context.resources.getString(type.event)
        } else {
            emptyString()
        }
    }

    fun getEventImage(context: Context): Drawable? {
        return if (type != null) {
            ContextCompat.getDrawable(context, type.icon)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_add)
        }
    }

    fun hasComment(): Boolean {
        return when (type) {
            EventType.CommitCommentEvent -> true
            EventType.IssueCommentEvent -> true
            EventType.PullRequestReviewCommentEvent -> true
            else -> false
        }
    }


    fun eventFullTitle(context: Context): SpannableStringBuilder {
        return SpannableStringBuilder().append(actor.displayLogin)
            .append(" ")
            .bold { append(getEventType(context)) }
            .append(" ")
            .append(repo.name)
    }

    fun getTime(): CharSequence {
        return getTimeAgo(createdAt)
    }
}


@JsonClass(generateAdapter = true)
data class Org(
    @field:Json(name = "avatar_url")
    val avatarUrl: String?,
    @field:Json(name = "gravatar_id")
    val gravatarId: String?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "login")
    val login: String?,
    @field:Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class Payload(
    @field:Json(name = "action")
    val action: String?,
    @field:Json(name = "issue")
    val issue: Issue?,
    @field:Json(name = "comment")
    val comment: Comment?,
)


@JsonClass(generateAdapter = true)
data class Repo(
    @field:Json(name = "id")
    val id: Int?,
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
    val id: Int?,
    @field:Json(name = "login")
    val login: String?,
    @field:Json(name = "url")
    val url: String?
)


@JsonClass(generateAdapter = true)
data class Issue(
    @field:Json(name = "active_lock_reason")
    val activeLockReason: Any?,
    @field:Json(name = "assignee")
    val assignee: Any?,
    @field:Json(name = "assignees")
    val assignees: List<Any>?,
    @field:Json(name = "author_association")
    val authorAssociation: String,
    @field:Json(name = "body")
    val body: String?,
    @field:Json(name = "closed_at")
    val closedAt: Any?,
    @field:Json(name = "comments")
    val comments: Int?,
    @field:Json(name = "comments_url")
    val commentsUrl: String?,
    @field:Json(name = "created_at")
    val createdAt: String?,
    @field:Json(name = "events_url")
    val eventsUrl: String?,
    @field:Json(name = "html_url")
    val htmlUrl: String?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "labels")
    val labels: List<Any?>,
    @field:Json(name = "labels_url")
    val labelsUrl: String?,
    @field:Json(name = "locked")
    val locked: Boolean,
    @field:Json(name = "milestone")
    val milestone: Any?,
    @field:Json(name = "node_id")
    val nodeId: String?,
    @field:Json(name = "number")
    val number: Int?,
    @field:Json(name = "performed_via_github_app")
    val performedViaGithubApp: Any?,
    @field:Json(name = "reactions")
    val reactions: Reactions,
    @field:Json(name = "repository_url")
    val repositoryUrl: String?,
    @field:Json(name = "state")
    val state: String?,
    @field:Json(name = "timeline_url")
    val timelineUrl: String?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "updated_at")
    val updatedAt: String?,
    @field:Json(name = "url")
    val url: String?,
    @field:Json(name = "user")
    val user: UserModel
)

@JsonClass(generateAdapter = true)
data class Reactions(
    @Json(name = "confused")
    val confused: Int?,
    @Json(name = "eyes")
    val eyes: Int?,
    @Json(name = "heart")
    val heart: Int?,
    @Json(name = "hooray")
    val hooray: Int?,
    @Json(name = "laugh")
    val laugh: Int?,
    @Json(name = "rocket")
    val rocket: Int?,
    @Json(name = "total_count")
    val totalCount: Int?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "+1")
    val plusOne: Int?,
    @Json(name = "-1")
    val minusOne: Int?
)

@JsonClass(generateAdapter = true)
data class Comment(
    @Json(name = "author_association")
    val authorAssociation: String?,
    @Json(name = "body")
    val body: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "html_url")
    val htmlUrl: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "issue_url")
    val issueUrl: String?,
    @Json(name = "node_id")
    val nodeId: String?,
    @Json(name = "performed_via_github_app")
    val performedViaGithubApp: Any?,
    @Json(name = "reactions")
    val reactions: Reactions,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "user")
    val user: UserModel
)