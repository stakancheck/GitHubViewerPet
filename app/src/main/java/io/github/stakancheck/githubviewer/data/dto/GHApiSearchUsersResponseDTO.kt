/*
 * Copyright (c) 2025 Artem Sukhanov (Stakancheck)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.stakancheck.githubviewer.data.dto

import io.github.stakancheck.githubviewer.data.dto.common.TextMatchDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GHApiSearchUsersResponseDTO(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("incomplete_results") val incompleteResults: Boolean,
    val items: List<UserItemDTO>
)

@Serializable
data class UserItemDTO(
    val login: String,
    val id: Long,
    @SerialName("node_id") val nodeId: String,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("gravatar_id") val gravatarId: String? = null,
    val url: String,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("followers_url") val followersUrl: String,
    @SerialName("subscriptions_url") val subscriptionsUrl: String,
    @SerialName("organizations_url") val organizationsUrl: String,
    @SerialName("repos_url") val reposUrl: String,
    @SerialName("received_events_url") val receivedEventsUrl: String,
    val type: String,
    val score: Double,
    @SerialName("following_url") val followingUrl: String,
    @SerialName("gists_url") val gistsUrl: String,
    @SerialName("starred_url") val starredUrl: String,
    @SerialName("events_url") val eventsUrl: String,
    @SerialName("public_repos") val publicRepos: Int? = null,
    @SerialName("public_gists") val publicGists: Int? = null,
    val followers: Int? = null,
    val following: Int? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    val name: String? = null,
    val bio: String? = null,
    val email: String? = null,
    val location: String? = null,
    @SerialName("site_admin") val siteAdmin: Boolean,
    val hireable: Boolean? = null,
    @SerialName("text_matches") val textMatches: List<TextMatchDTO>? = null,
    val blog: String? = null,
    val company: String? = null,
    @SerialName("suspended_at") val suspendedAt: String? = null,
    @SerialName("user_view_type") val userViewType: String,
)

