package com.aykuttasil.data.entities

import androidx.room.Entity
import com.aykuttasil.domain.entities.UserEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userdata", primaryKeys = ["id"])
data class UserData(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("login") val login: String? = null,
    @Expose @SerializedName("avatar_url") val avatarUrl: String? = null,
    @Expose @SerializedName("gravatar_id") val gravatarId: String? = null,
    @Expose @SerializedName("url") val url: String? = null,
    @Expose @SerializedName("html_url") val htmlUrl: String? = null,
    @Expose @SerializedName("followers_url") val followersUrl: String? = null,
    @Expose @SerializedName("following_url") val followingUrl: String? = null,
    @Expose @SerializedName("gists_url") val gistsUrl: String? = null,
    @Expose @SerializedName("starred_url") val starredUrl: String? = null,
    @Expose @SerializedName("subscriptions_url") val subscriptionsUrl: String? = null,
    @Expose @SerializedName("organizations_url") val organizationsUrl: String? = null,
    @Expose @SerializedName("repos_url") val reposUrl: String? = null,
    @Expose @SerializedName("events_url") val eventsUrl: String? = null,
    @Expose @SerializedName("received_events_url") val receivedEventsUrl: String? = null,
    @Expose @SerializedName("type") val type: String? = null,
    @Expose @SerializedName("site_admin") val siteAdmin: Boolean? = null,
    @Expose @SerializedName("name") val name: String? = null,
    @Expose @SerializedName("company") val company: String? = null,
    @Expose @SerializedName("blog") val blog: String? = null,
    @Expose @SerializedName("location") val location: String? = null,
    @Expose @SerializedName("email") val email: String? = null,
    @Expose @SerializedName("hireable") val hireable: Boolean? = null,
    @Expose @SerializedName("bio") val bio: String? = null,
    @Expose @SerializedName("public_repos") val publicRepos: Int? = null,
    @Expose @SerializedName("public_gists") val publicGists: Int? = null,
    @Expose @SerializedName("followers") val followers: Int? = null,
    @Expose @SerializedName("following") val following: Int? = null,
    @Expose @SerializedName("created_at") val createdAt: String? = null,
    @Expose @SerializedName("updated_at") val updatedAt: String? = null
)

fun UserData.toUserEntity(): UserEntity {
    return UserEntity(
        userId = id,
        userName = name,
        userEmail = login
    )
}

fun UserEntity.toUserData(): UserData {
    return UserData(
        id = userId!!,
        name = userName
    )
}